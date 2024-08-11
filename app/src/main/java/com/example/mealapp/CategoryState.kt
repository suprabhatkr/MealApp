package com.example.mealapp

data class CategoryState(
    val list : MutableList<Category> = mutableListOf(),
    val error : String = "",
    val loading : Boolean = true
)
