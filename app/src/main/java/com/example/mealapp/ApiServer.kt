package com.example.mealapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

private val retrofit = Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/").
addConverterFactory(GsonConverterFactory.create()).
build()

val recipeService: ApiServer = retrofit.create(ApiServer::class.java)

interface ApiServer {
    @GET("categories.php")
    suspend fun getCategories() : CategoryResponse
    @GET("filter.php")
    suspend fun getMeal(@Query("c") mealType: String): MealResponse
}