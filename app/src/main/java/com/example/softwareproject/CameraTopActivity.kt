package com.example.softwareproject

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.softwareproject.api.ClothesAPI
import com.example.softwareproject.databinding.ActivityCameraTopBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream

class CameraTopActivity :AppCompatActivity() {

    lateinit var binding: ActivityCameraTopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCameraTopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cameraTopShootTv.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            Log.d("카메라", intent.toString())
            getAction.launch(intent)
            binding.cameraTopShootTv.visibility = View.GONE
            binding.cameraTopCompleteTv.visibility = View.VISIBLE
            binding.cameraTopReshootTv.visibility = View.VISIBLE
        }

        binding.cameraTopReshootTv.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            getAction.launch(intent)
        }

        binding.cameraTopReshootTv.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

    val getAction = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        val bitmap = it?.data?.extras?.get("data") as Bitmap
        val push = encodeImage(bitmap).toString()
        Log.d("카메라 이미지 서버로 보내기",push)

        val gson : Gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://54.180.122.213:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val clothesAPI = retrofit.create(ClothesAPI::class.java)

        val data = Clothes(push)
        clothesAPI.postClothesTop(data).enqueue(object : Callback<ClothesResponse> {
            override fun onResponse(
                call: Call<ClothesResponse>,
                response: Response<ClothesResponse>
            ) {
                Log.d("이미지 정보 가져오기 성공",response.body()?.color.toString())
                binding.cameraTopIv.text = response.body()?.color.toString() + " " + response.body()?.pattern.toString() + " " + response.body()?.category.toString() + "가 인식 되었습니다."
            }

            override fun onFailure(call: Call<ClothesResponse>, t: Throwable) {
                Log.d("이미지 정보 가져오기 실패",t.message.toString())
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