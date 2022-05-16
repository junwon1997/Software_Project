package com.example.softwareproject

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
    }
}