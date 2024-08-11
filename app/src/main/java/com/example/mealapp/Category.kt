package com.example.mealapp

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)

data class CategoryResponse(
    val categories : MutableList<Category>
)