package com.example.softwareproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.softwareproject.databinding.ActivityClothesRecommendBinding

class ClothesRecommendActivity : AppCompatActivity() {

    lateinit var binding: ActivityClothesRecommendBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClothesRecommendBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.clothesRecommendBack.setOnClickListener {
            finish()
        }
    }
}