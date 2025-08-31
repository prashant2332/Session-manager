package com.example.sessionmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.sessionmanager.ui.session.AllSessionActivity
import com.example.sessionmanager.ui.session.SearchActivity
import com.example.sessionmanager.ui.session.StartSessionActivity

class MainActivity : AppCompatActivity() {
    private lateinit var startSessionBtn: Button
    private lateinit var searchSessionBtn: Button
    private lateinit var allSessionsBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startSessionBtn = findViewById(R.id.startSessionBtn)
        searchSessionBtn = findViewById(R.id.searchSessionBtn)
        allSessionsBtn=findViewById(R.id.allSessionsBtn)

        startSessionBtn.setOnClickListener {
            val intent = Intent(this, StartSessionActivity::class.java)
            startActivity(intent)
        }

        searchSessionBtn.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }
        allSessionsBtn.setOnClickListener {
            val intent = Intent(this, AllSessionActivity::class.java)
            startActivity(intent)
        }
    }
}