package com.example.imagesapp.adapters

import android.graphics.Bitmap
import android.net.Uri
import android.util.Size
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesapp.databinding.ImageItemBinding

class ImagesViewHolder(view:View):RecyclerView.ViewHolder(view) {
    val binding = ImageItemBinding.bind(view)
    fun render(uri:Uri){
       //binding.img.setImageURI(uri)
        val thumbnail: Bitmap =
            binding.imgItem.context.contentResolver.loadThumbnail(
                uri, Size(640, 480), null)
        binding.img.setImageBitmap(thumbnail)
    }
}