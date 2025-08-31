package com.example.sessionmanager.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sessions")
data class Session(
    @PrimaryKey val sessionId: String,
    val name: String,
    val age: Int,
    val timestamp: Long = System.currentTimeMillis()
)