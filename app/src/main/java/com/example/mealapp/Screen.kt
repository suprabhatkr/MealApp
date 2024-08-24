package com.example.mealapp

import okhttp3.Route

sealed class Screen (val route: String){
    data object CategoryScreen:Screen("category")
    data object MealScreen:Screen("meal")
}