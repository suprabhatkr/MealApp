package com.example.mealapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder().baseUrl("www.themealdb.com/api/json/v1/1").
addConverterFactory(GsonConverterFactory.create()).
build()

val recipeService = retrofit.create(ApiServer::class.java)

interface ApiServer {
    @GET("categories.php")
    suspend fun getCategories() : CategoryResponse
}