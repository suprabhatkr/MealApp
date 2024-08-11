package com.example.mealapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel(){

    private val _categoryState : MutableState<CategoryState> = mutableStateOf(CategoryState())
    var categoryState : MutableState<CategoryState> = _categoryState

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val categoryList = recipeService.getCategories().categories
                _categoryState.value = _categoryState.value.copy(
                    list = categoryList,
                    loading = false
                )
            } catch (e : Exception) {
                _categoryState.value = _categoryState.value.copy(
                    loading = false,
                    error = "Not loaded : error is ${e.message}"
                )
            }
        }
    }
}