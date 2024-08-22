package com.example.mealapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mealapp.ui.theme.MealAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MealAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Categories(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Categories(modifier: Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "categories") {
        composable("categories") {
            ShowCategories(modifier = modifier,
                navController = navController)
        }
        composable("meals/{mealType}") { backStackEntry ->
            val mealType = backStackEntry.arguments?.getString("mealType") ?: ""
            MealView(modifier = modifier, mealType = mealType) }
    }
}