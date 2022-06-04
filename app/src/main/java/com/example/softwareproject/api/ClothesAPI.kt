package com.example.softwareproject.api

import com.example.softwareproject.Clothes
import com.example.softwareproject.ClothesResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ClothesAPI{

    @POST("/clothes/test_image/1")
    fun postClothesTop(@Body clothes: Clothes):Call<ClothesResponse>

    @POST("/clothes/test_image/2")
    fun postClothesBottom(@Body clothes: Clothes):Call<ClothesResponse>

    @GET("/clothes/test_image/1")
    fun getClothesTop(@Body clothes: Clothes):Call<ClothesResponse>

    @GET("/clothes/test_image/2")
    fun getClothesBottom(@Body clothes: Clothes):Call<ClothesResponse>

}