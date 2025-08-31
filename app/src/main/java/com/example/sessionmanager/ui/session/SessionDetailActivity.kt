package com.example.sessionmanager.ui.session

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sessionmanager.R
import com.example.sessionmanager.ui.adapter.ImageAdapter
import com.example.sessionmanager.viewmodel.SessionViewModel
import java.io.File

class SessionDetailActivity : AppCompatActivity() {
    private val sessionViewModel: SessionViewModel by viewModels()
    private lateinit var detailText: TextView
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: ImageAdapter
    private lateinit var sessionId: String
    private lateinit var noImagesText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_session_detail)

        detailText = findViewById(R.id.detailText)
        recyclerview = findViewById(R.id.detailImagesRecycler)
        noImagesText = findViewById(R.id.noImagesText)

        recyclerview.layoutManager = GridLayoutManager(this, 2)
        adapter = ImageAdapter { file ->
            showImageDialog(file)
        }
        recyclerview.adapter = adapter

        sessionId = intent.getStringExtra("SESSION_ID") ?: ""

        sessionViewModel.getSessionById(sessionId) { session ->
            runOnUiThread {
                if (session != null) {
                    detailText.text = "ID: ${session.sessionId}\nName: ${session.name}\nAge: ${session.age}"

                    val imagesDir = File(externalMediaDirs.first(), "Sessionmanager/Sessions/$sessionId")
                    val images = imagesDir.listFiles()?.toList() ?: emptyList()
                    if (images.isEmpty()) {
                        recyclerview.visibility = View.GONE
                        noImagesText.visibility = View.VISIBLE
                    } else {
                        recyclerview.visibility = View.VISIBLE
                        noImagesText.visibility = View.GONE
                        adapter.submitList(images)
                    }
                } else {
                    detailText.text = "Session not found"
                }
            }
        }
    }

    private fun showImageDialog(imageFile: File) {
        val dialogView = layoutInflater.inflate(R.layout.fullscreendialog, null)
        val imageView = dialogView.findViewById<ImageView>(R.id.fullScreenImage)
        val closeBtn = dialogView.findViewById<ImageButton>(R.id.closeBtn)

        Glide.with(this).load(imageFile).into(imageView)

        val dialog = android.app.AlertDialog.Builder(this, android.R.style.Theme_Black_NoTitleBar_Fullscreen)
            .setView(dialogView)
            .create()

        closeBtn.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }
}