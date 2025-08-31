package com.example.sessionmanager.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sessionmanager.data.model.Session

@Dao
interface SessionDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertSession(session: Session)

    @Query("SELECT * FROM sessions WHERE sessionId = :id")
    suspend fun getSessionById(id: String): Session?

    @Query("SELECT * FROM sessions")
    suspend fun getAllSessions(): List<Session>

    @Query("SELECT COUNT(*) FROM sessions WHERE sessionId = :id")
    suspend fun sessionExists(id: String): Int

    @Delete
    suspend fun deleteSession(session: Session)

}
