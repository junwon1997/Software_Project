package com.example.softwareproject

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.softwareproject.databinding.ActivityClosetBinding

class ClosetActivity : AppCompatActivity() {

    lateinit var binding : ActivityClosetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClosetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.closetBack.setOnClickListener {
            finish()
        }
    }
}