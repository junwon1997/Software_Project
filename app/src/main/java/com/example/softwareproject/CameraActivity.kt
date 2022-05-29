package com.example.softwareproject

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.softwareproject.api.ClothesAPI
import com.example.softwareproject.databinding.ActivityCameraBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream


class CameraActivity : AppCompatActivity() {

    lateinit var binding: ActivityCameraBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cameraShootTv.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if(intent.resolveActivity(packageManager) != null){
                getAction.launch(intent)
            }
        }
    }

    val getAction = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        val bitmap = it?.data?.extras?.get("data") as Bitmap
        binding.cameraIv.setImageBitmap(bitmap)
        val push = encodeImage(bitmap).toString()
        Log.d("태그", push)

        val gson : Gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val clothesAPI = retrofit.create(ClothesAPI::class.java)

        val data = Clothes(push)
        clothesAPI.postClothes(data).enqueue(object : Callback<ClothesResponse>{
            override fun onResponse(
                call: Call<ClothesResponse>,
                response: Response<ClothesResponse>
            ) {
                Log.d("log",response.toString())
            }

            override fun onFailure(call: Call<ClothesResponse>, t: Throwable) {
                Log.d("log",t.message.toString())
            }

        })

    }

    private fun encodeImage(bitmap: Bitmap):String? {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b,Base64.DEFAULT)
    }

}