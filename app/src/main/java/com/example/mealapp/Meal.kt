package com.example.mealapp

data class Meal(
    val strMeal : String,
    val strMealThumb : String,
    val idMeal : String
)

data class MealResponse(
    val meals : MutableList<Meal>
)
