package com.example.imagesapp.adapters

import android.graphics.Bitmap
import android.net.Uri
import android.util.Size
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.imagesapp.databinding.ImageItemBinding
import com.example.imagesapp.model.MyImage

class ImagesViewHolder(view:View):RecyclerView.ViewHolder(view) {
    val binding = ImageItemBinding.bind(view)
    fun render(image:MyImage,onSelectedImage:(MyImage)->Unit){
       //binding.img.setImageURI(uri)
        val thumbnail: Bitmap =
            binding.imgItem.context.contentResolver.loadThumbnail(
                image.uri, Size(640, 480), null)
        binding.img.setImageBitmap(thumbnail)
        binding.imgItem.setOnClickListener{
            onSelectedImage(image)
        }
        binding.btnDelete.visibility = if(image.delete){
            View.VISIBLE
        }else{
           View.GONE
        }
    }
}