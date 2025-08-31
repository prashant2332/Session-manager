package com.example.sessionmanager.repository

import android.content.Context
import com.example.sessionmanager.data.db.SessionDao
import com.example.sessionmanager.data.model.Session
import java.io.File



class SessionRepository(private val context: Context, private val sessionDao: SessionDao) {

    suspend fun insertSession(session: Session) {
        sessionDao.insertSession(session)
    }

    suspend fun getSessionById(sessionId: String): Session? {
        return sessionDao.getSessionById(sessionId)
    }

    suspend fun getAllSessions(): List<Session> {
        return sessionDao.getAllSessions()
    }

    suspend fun isSessionExists(sessionId: String): Boolean {
        return sessionDao.sessionExists(sessionId) > 0
    }

    suspend fun deleteSession(session: Session) {
        sessionDao.deleteSession(session)
        deleteSessionFolder(session.sessionId)
    }

    private fun deleteSessionFolder(sessionId: String) {
        val folder = File(context.getExternalMediaDirs().first(), "Sessionmanager/Sessions/$sessionId")
        if (folder.exists()) {
            folder.deleteRecursively()
        }
    }
}
