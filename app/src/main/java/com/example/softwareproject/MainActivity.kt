package com.example.softwareproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.softwareproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainClothesScan.setOnClickListener {
            val intent = Intent(this, ClothesScanActivity::class.java)
            startActivity(intent)
        }

        binding.mainCloset.setOnClickListener {
            val intent = Intent(this, ClosetActivity::class.java)
            startActivity(intent)
        }

        binding.mainClothesRecommend.setOnClickListener {
            val intent = Intent(this, ClothesRecommendActivity::class.java)
            startActivity(intent)
        }


    }
}