package com.example.capstoneproject.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.capstoneproject.dao.IngredientDao
import com.example.capstoneproject.databases.IngredientDatabase
import com.example.capstoneproject.models.Ingredient

class IngredientRepository(context: Context) {
    private val ingredientDao: IngredientDao

    init {
        val database = IngredientDatabase.getDatabase(context)
        ingredientDao = database!!.ingredientDao()
    }

    fun getAllIngredients(): LiveData<List<Ingredient>> {
        return ingredientDao.getAllIngredients()
    }

    suspend fun addIngredient(ingredient: Ingredient) {
        ingredientDao.addIngredient(ingredient)
    }

    suspend fun deleteIngredient(ingredient: Ingredient) {
        ingredientDao.deleteIngredient(ingredient)
    }

    suspend fun deleteAllIngredients() {
        ingredientDao.deleteAllIngredients()
    }

}