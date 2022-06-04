package com.example.softwareproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.softwareproject.databinding.ActivityClosetBottomBinding

class ClosetBottomActivity: AppCompatActivity() {
    lateinit var binding: ActivityClosetBottomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClosetBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}