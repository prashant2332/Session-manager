package com.example.sessionmanager.ui.session

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sessionmanager.R
import com.example.sessionmanager.ui.adapter.ImageAdapter
import com.example.sessionmanager.viewmodel.SessionViewModel
import java.io.File

class SearchActivity : AppCompatActivity() {
    private lateinit var searchinput: EditText
    private lateinit var searchbtn: Button
    private lateinit var result: TextView
    private lateinit var recyclerview: RecyclerView
    private lateinit var adapter: ImageAdapter
    private lateinit var noImagesText:TextView

    private val sessionViewModel: SessionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchinput = findViewById(R.id.searchInput)
        searchbtn = findViewById(R.id.searchBtn)
        result = findViewById(R.id.resultText)
        recyclerview = findViewById(R.id.imageRecycler)
        noImagesText=findViewById(R.id.noImagesText)

        recyclerview.layoutManager = GridLayoutManager(this, 2)
        adapter = ImageAdapter{ file -> showImageDialog(file) }
        recyclerview.adapter = adapter

        searchbtn.setOnClickListener {
            val sessionId = searchinput.text.toString().trim()
            if (sessionId.isNotEmpty()) {

                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(it.windowToken, 0)

                sessionViewModel.getSessionById(sessionId) { session ->
                    if (session != null) {
                        result.visibility= View.VISIBLE
                        result.text = "ID: ${session.sessionId}\nName: ${session.name}\nAge: ${session.age}"

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
                        result.visibility=View.VISIBLE
                        result.text = "No session found."
                        adapter.submitList(emptyList())
                    }
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