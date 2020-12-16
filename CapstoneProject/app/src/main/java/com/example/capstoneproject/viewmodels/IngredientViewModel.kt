package com.example.capstoneproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.capstoneproject.models.Ingredient
import com.example.capstoneproject.repository.IngredientRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class IngredientViewModel(application: Application) : AndroidViewModel(application) {

    private var mainScope = CoroutineScope(Dispatchers.Main)
    private val ingredientRepository: IngredientRepository = IngredientRepository(application.applicationContext)
    val ingredients = ingredientRepository.getAllIngredients()

    fun addIngredient(ingredient: Ingredient) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
            ingredientRepository.addIngredient(ingredient)
            }
        }
    }

    fun deleteIngredient(ingredient: Ingredient) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                ingredientRepository.deleteIngredient(ingredient)
            }
        }
    }

    fun deleteAllIngredients() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                ingredientRepository.deleteAllIngredients()
            }
        }
    }
}