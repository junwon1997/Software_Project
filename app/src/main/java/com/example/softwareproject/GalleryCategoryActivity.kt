package com.example.softwareproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.softwareproject.databinding.ActivityGalleryCategoryBinding

class GalleryCategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityGalleryCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.galleryCategoryBack.setOnClickListener {
            finish()
        }

        binding.galleryCategoryTop.setOnClickListener {
            val intent = Intent(this, GalleryTopActivity::class.java)
            startActivity(intent)
        }

        binding.galleryCategoryBottom.setOnClickListener {
            val intent = Intent(this, GalleryBottomActivity::class.java)
            startActivity(intent)
        }
    }
}