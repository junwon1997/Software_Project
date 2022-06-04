package com.example.softwareproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.softwareproject.databinding.ActivityClosetTopBinding

class ClosetTopActivity : AppCompatActivity() {
    lateinit var binding: ActivityClosetTopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClosetTopBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}