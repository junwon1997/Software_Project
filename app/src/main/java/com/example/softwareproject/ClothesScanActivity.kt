package com.example.softwareproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.softwareproject.databinding.ActivityClothesScanBinding

class ClothesScanActivity : AppCompatActivity() {

    lateinit var binding: ActivityClothesScanBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClothesScanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.clothesScanBack.setOnClickListener {
            finish()
        }

        binding.clothesScanPicture.setOnClickListener {
            val intent = Intent(this, CameraCategoryActivity::class.java)
            startActivity(intent)
        }

        binding.clothesScanGallery.setOnClickListener {
            val intent = Intent(this, GalleryCategoryActivity::class.java)
            startActivity(intent)
        }

    }
}


