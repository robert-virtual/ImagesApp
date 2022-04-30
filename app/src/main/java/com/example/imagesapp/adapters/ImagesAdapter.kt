package com.example.imagesapp.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesapp.R

class ImagesAdapter(private val images:List<Uri>):RecyclerView.Adapter<ImagesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ImagesViewHolder(layoutInflater.inflate(R.layout.image_item,parent,false))

    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.render(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }

}