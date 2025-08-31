package com.example.sessionmanager.ui.session

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import android.widget.Toast
import com.example.sessionmanager.R
import com.example.sessionmanager.data.model.Session
import com.example.sessionmanager.viewmodel.SessionViewModel

class EndSessionActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var age: EditText
    private lateinit var savebtn: Button
    private lateinit var sessionId: String

    private val sessionViewModel: SessionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_session)

        name= findViewById(R.id.nameInput)
        age= findViewById(R.id.ageInput)
        savebtn= findViewById(R.id.saveBtn)

        sessionId = intent.getStringExtra("SESSION_ID") ?: ""

        savebtn.setOnClickListener {
            val name = name.text.toString()
            val age = age.text.toString().toIntOrNull()

            if (name.isEmpty() || age == null) {
                Toast.makeText(this, "Enter valid name and age", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val session = Session(
                sessionId = sessionId,
                name = name,
                age = age
            )
            sessionViewModel.insertSession(session)

            Toast.makeText(this, "Session saved!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}