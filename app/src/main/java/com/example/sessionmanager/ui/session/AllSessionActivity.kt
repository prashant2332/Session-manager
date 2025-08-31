package com.example.sessionmanager.ui.session

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sessionmanager.R
import com.example.sessionmanager.data.model.Session
import com.example.sessionmanager.ui.adapter.SessionListAdapter
import com.example.sessionmanager.viewmodel.SessionViewModel

class AllSessionActivity : AppCompatActivity() {

    private val sessionViewModel: SessionViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SessionListAdapter
    private lateinit var noSessionsText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_session)

        recyclerView = findViewById(R.id.sessionsRecycler)
        recyclerView.layoutManager = LinearLayoutManager(this)
        noSessionsText = findViewById(R.id.noSessionsText)

        adapter = SessionListAdapter ({ session ->
            val intent = Intent(this, SessionDetailActivity::class.java)
            intent.putExtra("SESSION_ID", session.sessionId)
            startActivity(intent)
        },
        onDeleteClick = { session ->
            showDeleteDialog(session)
        })
        recyclerView.adapter = adapter

        loadSessions()
    }

    private fun loadSessions() {
        sessionViewModel.getAllSessions { sessions ->
            runOnUiThread {
                if (sessions.isEmpty()) {
                    recyclerView.visibility = View.GONE
                    noSessionsText.visibility = View.VISIBLE
                } else {
                    recyclerView.visibility = View.VISIBLE
                    noSessionsText.visibility = View.GONE
                    adapter.submitList(sessions)
                }
            }
        }
    }

    private fun showDeleteDialog(session: Session) {
        AlertDialog.Builder(this)
            .setTitle("Delete Session")
            .setMessage("Are you sure for deleting this session?")
            .setPositiveButton("Yes") { _, _ ->
                sessionViewModel.deleteSession(session) {
                    runOnUiThread {
                        Toast.makeText(this, "Session deleted", Toast.LENGTH_SHORT).show()
                        sessionViewModel.getAllSessions { sessions ->
                            runOnUiThread { adapter.submitList(sessions) }
                        }
                    }
                }
            }
            .setNegativeButton("No", null)
            .show()
    }
}