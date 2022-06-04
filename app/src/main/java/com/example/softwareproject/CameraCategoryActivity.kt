package com.example.softwareproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.softwareproject.databinding.ActivityCameraCategoryBinding

class CameraCategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityCameraCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cameraCategoryTop.setOnClickListener {
            val intent = Intent(this,CameraTopActivity::class.java)
            startActivity(intent)
        }

        binding.cameraCategoryBottom.setOnClickListener {
            val intent = Intent(this, CameraBottomActivity::class.java)
            startActivity(intent)
        }

        binding.cameraCategoryBack.setOnClickListener {
            finish()
        }
    }
}