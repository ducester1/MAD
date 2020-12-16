package com.example.capstoneproject.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.capstoneproject.models.Ingredient

@Dao
interface IngredientDao {

    @Query("SELECT * FROM IngredientsTable")
    fun getAllIngredients(): LiveData<List<Ingredient>>

    @Insert
    suspend fun addIngredient(ingredient: Ingredient)

    @Delete
    suspend fun deleteIngredient(ingredient: Ingredient)

    @Query("DELETE FROM ingredientstable")
    suspend fun deleteAllIngredients()

}