package com.example.sessionmanager.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sessionmanager.R
import java.io.File

class ImageAdapter(private val onImageClick: (File) -> Unit) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    private var imageList: List<File> = emptyList()

    fun submitList(list: List<File>) {
        imageList = list
        notifyDataSetChanged()
    }

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.sessionImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int = imageList.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val file = imageList[position]
        Glide.with(holder.itemView.context)
            .load(file)
            .into(holder.imageView)

        holder.imageView.setOnClickListener {
            onImageClick(file)
        }
    }
}