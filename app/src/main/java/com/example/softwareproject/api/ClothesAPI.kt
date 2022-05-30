package com.example.softwareproject.api

import com.example.softwareproject.Clothes
import com.example.softwareproject.ClothesResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ClothesAPI{

    @POST("/clothes/test_image/1")
    fun postClothes(@Body clothes: Clothes):Call<ClothesResponse>

}