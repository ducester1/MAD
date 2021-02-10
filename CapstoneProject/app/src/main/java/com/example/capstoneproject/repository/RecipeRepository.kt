package com.example.capstoneproject.repository

import android.content.Context
import android.database.Observable
import androidx.lifecycle.LiveData
import com.example.capstoneproject.dao.RecipeDao
import com.example.capstoneproject.databases.RecipeDatabase
import com.example.capstoneproject.models.Recipe

class RecipeRepository(context: Context) {
    private val recipeDao: RecipeDao

    init {
        val database = RecipeDatabase.getDatabase(context)
        recipeDao = database!!.recipeDao()
    }

    fun getAllRecipes(): LiveData<List<Recipe>> {
        return recipeDao.getAllRecipes()
    }

    fun getRecipe(id: Long): LiveData<Recipe> {
        return recipeDao.getRecipe(id)
    }

    suspend fun addRecipe(recipe: Recipe) {
        recipeDao.addRecipe(recipe)
    }

    suspend fun updateRecipe(recipe: Recipe) {
        recipeDao.updateRecipe(recipe)
        println(recipe.id)
    }

    suspend fun deleteRecipe(recipe: Recipe) {
        recipeDao.deleteRecipe(recipe)
    }

    suspend fun deleteAllRecipe() {
        recipeDao.deleteAllRecipes()
    }

}