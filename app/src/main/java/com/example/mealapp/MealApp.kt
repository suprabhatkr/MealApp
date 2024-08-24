package com.example.mealapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MealApp(modifier : Modifier) {
    val navController = rememberNavController()

    val categoryViewModel : CategoryViewModel = viewModel()

    val categoryList = categoryViewModel.categoryState

    NavHost(navController = navController, startDestination = Screen.CategoryScreen.route) {
        composable(Screen.CategoryScreen.route) {
            ShowCategories(modifier = modifier,
                categoryList = categoryList,
                navigateToMeal = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("category", it)
                    navController.navigate(Screen.MealScreen.route)
                })
        }
        composable(Screen.MealScreen.route) {
            val category : Category = navController.previousBackStackEntry?.
            savedStateHandle?.get<Category>("category") ?:
            Category("", "","", "")

            MealView(modifier = modifier, category = category)
        }
    }
}