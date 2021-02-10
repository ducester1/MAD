package com.example.capstoneproject.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.capstoneproject.models.Ingredient
import com.example.capstoneproject.models.Recipe
import com.example.capstoneproject.repository.IngredientRepository
import com.example.capstoneproject.repository.RecipeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    private var mainScope = CoroutineScope(Dispatchers.Main)
    private val recipeRepository: RecipeRepository = RecipeRepository(application.applicationContext)
    val recipes = recipeRepository.getAllRecipes()

    fun getRecipe(id: Long): LiveData<Recipe> {
        return recipeRepository.getRecipe(id)
    }

    fun addRecipe(recipe: Recipe) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                recipeRepository.addRecipe(recipe)
            }
        }
    }

    fun updateRecipe(recipe: Recipe) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                recipeRepository.updateRecipe(recipe)
            }
        }
    }

    fun deleteRecipe(recipe: Recipe) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                recipeRepository.deleteRecipe(recipe)
            }
        }
    }

    fun deleteAllRecipes() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                recipeRepository.deleteAllRecipe()
            }
        }
    }
}