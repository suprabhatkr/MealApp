package com.example.mealapp

data class MealState(
    val list : MutableList<Meal> = mutableListOf(),
    val error : String = "",
    val loading : Boolean = true
)
