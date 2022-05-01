package com.example.imagesapp.adapters

import android.graphics.Bitmap
import android.net.Uri
import android.util.Size
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.updateLayoutParams
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
        if (image.delete){
            binding.btnDelete.setOnClickListener{
                onSelectedImage(image)
            }
        }else{
            binding.imgItem.setOnClickListener{
                onSelectedImage(image)
            }
            binding.img.updateLayoutParams {
                width = 150
                height = 150

            }
        }
        binding.btnDelete.visibility = if(image.delete){
            View.VISIBLE
        }else{
           View.GONE
        }
    }
}