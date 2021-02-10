package com.example.capstoneproject.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.capstoneproject.models.Recipe

@Dao
interface RecipeDao {

    @Query("SELECT * FROM RecipesTable")
    fun getAllRecipes(): LiveData<List<Recipe>>

    @Query("Select * FROM RecipesTable WHERE id = :id")
    fun getRecipe(id: Long): LiveData<Recipe>

    @Insert (onConflict = REPLACE)
    suspend fun addRecipe(recipe: Recipe)

    @Update (onConflict = REPLACE)
    suspend fun updateRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Query("DELETE FROM RecipesTable")
    suspend fun deleteAllRecipes()

}