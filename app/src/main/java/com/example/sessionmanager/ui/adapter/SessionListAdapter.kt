package com.example.sessionmanager.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sessionmanager.R
import com.example.sessionmanager.data.model.Session

class SessionListAdapter(
    private val onItemClick: (Session) -> Unit,
    private val onDeleteClick: (Session) -> Unit
) : RecyclerView.Adapter<SessionListAdapter.SessionViewHolder>() {

    private var sessions: List<Session> = emptyList()

    fun submitList(list: List<Session>) {
        sessions = list
        notifyDataSetChanged()
    }

    class SessionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.sessionIdText)
        val name: TextView = itemView.findViewById(R.id.sessionNameText)
        val age: TextView = itemView.findViewById(R.id.sessionAgeText)
        val deleteBtn: ImageButton = itemView.findViewById(R.id.deleteBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_session, parent, false)
        return SessionViewHolder(view)
    }

    override fun getItemCount(): Int = sessions.size

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        val session = sessions[position]
        holder.id.text = "SessionID: ${session.sessionId}"
        holder.name.text = "Name: ${session.name}"
        holder.age.text = "Age: ${session.age}"

        holder.itemView.setOnClickListener {
            onItemClick(session)
        }

        holder.deleteBtn.setOnClickListener { onDeleteClick(session) }
    }
}
