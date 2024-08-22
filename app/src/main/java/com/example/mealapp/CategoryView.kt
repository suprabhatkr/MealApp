package com.example.mealapp

import android.media.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter

@Composable
fun ShowCategories(modifier: Modifier, navController: NavController) {

    val categoryViewModel : CategoryViewModel = viewModel()

    val categoryList = categoryViewModel.categoryState
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            categoryList.value.loading -> {
               CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            categoryList.value.error != "" -> {
                Text(text = categoryList.value.error)
            }
            else -> {
                LazyVerticalGrid(
                    GridCells.Adaptive(152.dp),
                    modifier = Modifier.align(Alignment.Center),
                    content = {
                        items(categoryList.value.list.size) {
                            index -> ShowCategory(category = categoryList.value.list[index],
                                navigateController = navController)
                        }
                    }
                )
            }
        }
    }

}

@Composable
fun ShowCategory(category: Category, navigateController: NavController) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .border(1.dp, Color.Cyan),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        AsyncImage(
            model = category.strCategoryThumb,
            contentDescription = category.strCategoryDescription)

        Button(onClick = {
            navigateController.navigate("meals/${category.strCategory}")
        }) {
            Text(text = category.strCategory)
        }
    }

}