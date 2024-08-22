package com.example.mealapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch

class MealViewModel(mealType : String) : ViewModel() {

    private val _mealState = mutableStateOf(MealState())
    var mealState = _mealState

    init {
        fetchMeal(mealType)
    }

    private fun fetchMeal(mealType : String) {
        viewModelScope.launch {
            try {
                val mealList = recipeService.getMeal(mealType).meals
                _mealState.value = _mealState.value.copy(
                    list = mealList,
                    loading = false
                )
            } catch (e : Exception) {
                _mealState.value = _mealState.value.copy(
                    loading = false,
                    error = "Not loaded : error is ${e.message}"
                )
            }
        }
    }
}

class MealViewModelFactory(
    private val myParam: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealViewModel::class.java)) {
            return MealViewModel(myParam) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}