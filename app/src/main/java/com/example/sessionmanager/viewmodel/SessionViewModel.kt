package com.example.sessionmanager.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sessionmanager.data.db.AppDatabase
import com.example.sessionmanager.data.model.Session
import com.example.sessionmanager.repository.SessionRepository

import kotlinx.coroutines.launch

class SessionViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: SessionRepository
    init {
        val sessionDao = AppDatabase.getDatabase(application).sessionDao()
        repository = SessionRepository(application,sessionDao)
    }
    fun insertSession(session: Session) {
        viewModelScope.launch {
            repository.insertSession(session)
        }
    }
    fun getSessionById(sessionId: String, onResult: (Session?) -> Unit) {
        viewModelScope.launch {
            val session = repository.getSessionById(sessionId)
            onResult(session)
        }
    }
    fun getAllSessions(onResult: (List<Session>) -> Unit) {
        viewModelScope.launch {
            val sessions = repository.getAllSessions()
            onResult(sessions)
        }
    }

    fun checkSessionId(sessionId: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val exists = repository.isSessionExists(sessionId)
            onResult(exists)
        }
    }

    fun deleteSession(session: Session, onComplete: () -> Unit) {
        viewModelScope.launch {
            repository.deleteSession(session)
            onComplete()
        }
    }

}
