package com.example.mealapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage

@Composable
fun MealView(modifier: Modifier,
             mealType : String = "lamb") {

    val factory = MealViewModelFactory(mealType)
    val mealViewModel : MealViewModel = viewModel(factory = factory)
    val mealState = mealViewModel.mealState

    Box(modifier = modifier) {
        when {
            mealState.value.loading -> {
                CircularProgressIndicator(progress = { 1f })
            }
            mealState.value.error != "" -> {
                Text(text = mealState.value.error)
            }
            else -> {
                Column (
                    modifier = Modifier.fillMaxSize(),
                    Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = mealType)
                    LazyVerticalGrid(columns = GridCells.Adaptive(152.dp),
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        content = {
                            items(mealState.value.list) {
                                    meal -> MealDetails(meal = meal)
                            }
                        })
                }
            }
        }
    }
}

@Composable
fun MealDetails(meal : Meal) {
    Column (modifier = Modifier.padding(8.dp)){
        AsyncImage(model = meal.strMealThumb,
            contentDescription = "${meal.strMeal} image")
        Text(text = meal.strMeal)
    }
}