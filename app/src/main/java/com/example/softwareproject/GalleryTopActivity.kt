package com.example.softwareproject

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.softwareproject.api.ClothesAPI
import com.example.softwareproject.databinding.ActivityGalleryTopBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ByteArrayOutputStream

class GalleryTopActivity : AppCompatActivity() {

    lateinit var binding: ActivityGalleryTopBinding

    @RequiresApi(Build.VERSION_CODES.P)
    private val selectPictureLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){
        val bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver,it))
        val push = encodeImage(bitmap).toString()
        Log.d("갤러리 이미지",push)

        val gson : Gson = GsonBuilder().setLenient().create()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://54.180.122.213:8080")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val clothesAPI = retrofit.create(ClothesAPI::class.java)

        val data = Clothes(push)
        clothesAPI.postClothesBottom(data).enqueue(object : Callback<ClothesResponse> {
            override fun onResponse(
                call: Call<ClothesResponse>,
                response: Response<ClothesResponse>
            ) {
                Log.d("이미지 정보 가져오기 성공",response.body().toString())
                binding.galleryTopIv.text = response.body()?.color.toString() + " " + response.body()?.pattern.toString() + " " + response.body()?.category.toString() + "가 인식 되었습니다."
            }

            override fun onFailure(call: Call<ClothesResponse>, t: Throwable) {
                Log.d("이미지 정보 가져오기 실패",t.message.toString())
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryTopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.galleryTopShootTv.setOnClickListener {
            selectPictureLauncher.launch("image/*")
        }
    }

    private fun encodeImage(bitmap: Bitmap):String? {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
}