package com.example.sessionmanager.ui.session

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.example.sessionmanager.R
import com.example.sessionmanager.viewmodel.SessionViewModel
import java.util.UUID


class StartSessionActivity : AppCompatActivity() {

    private lateinit var sessionIdinput: EditText
    private lateinit var startCaptureBtn: Button
    private val sessionViewModel: SessionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_session)

        sessionIdinput = findViewById(R.id.sessionIdInput)
        startCaptureBtn = findViewById(R.id.startCaptureBtn)

        startCaptureBtn.setOnClickListener {
            var sessionId = sessionIdinput.text.toString().trim()
            if (sessionId.isEmpty()) {
                sessionIdinput.error="Enter the SessionId"
                return@setOnClickListener
            }

            sessionViewModel.checkSessionId(sessionId) { exists ->
                if (exists) {
                        Toast.makeText(this, "Session ID already exists!", Toast.LENGTH_SHORT).show()
                } else {
                    val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(it.windowToken, 0)
                    val intent = Intent(this, CaptureActivity::class.java)
                    intent.putExtra("SESSION_ID", sessionId)
                    startActivity(intent)
                }
            }

        }
    }
}
