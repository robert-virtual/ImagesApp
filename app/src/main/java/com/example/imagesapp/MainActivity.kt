package com.example.imagesapp

import android.content.ContentUris
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagesapp.adapters.ImagesAdapter
import com.example.imagesapp.databinding.ActivityMainBinding
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {



    private lateinit var binding : ActivityMainBinding
    val images = mutableListOf<Uri>()
    private val imagesAdapter = ImagesAdapter(images)
    val requestlauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        if (it){
            loadImages()
        }else{
            Toast.makeText(this, "Acceso a imagenes denegado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestpermission()
        binding.floatingBtn.setOnClickListener {
            Toast.makeText(this, "Floating button", Toast.LENGTH_SHORT).show()
        }
        initRecyclerView()
    }
    fun initRecyclerView(){
        //binding.recyclerView.layoutManager = GridLayoutManager(this, 4)
        binding.recyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.recyclerView.adapter = imagesAdapter
    }
   fun requestpermission(){
       when{
           ContextCompat.checkSelfPermission(
               applicationContext,
               android.Manifest.permission.READ_EXTERNAL_STORAGE,
           ) == PackageManager.PERMISSION_GRANTED->{
              loadImages()
           }
           else ->{
              requestlauncher.launch(
                  android.Manifest.permission.READ_EXTERNAL_STORAGE,
              )
           }
       }
   }
    fun loadImages(){

        val resolver = contentResolver
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val cursor  = resolver.query(uri,null,null,null,"${MediaStore.Images.Media.DATE_TAKEN} DESC")
        when{
            cursor == null ->{
                Toast.makeText(this, "Hubo un Error", Toast.LENGTH_SHORT).show()
            }
            !cursor.moveToFirst() ->{
                Toast.makeText(this, "No se encontraron imagenes", Toast.LENGTH_SHORT).show()
            }
            else ->{
                val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)
               do {
                   val id = cursor.getLong(idColumn)
                   val uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,id)
                   images.add(uri)
                   imagesAdapter.notifyItemInserted(images.size-1)

               }while(cursor.moveToNext())


                Toast.makeText(this, "${cursor.count} imagenes encontradas", Toast.LENGTH_SHORT).show()
            }
        }
        cursor?.close()
    }
}