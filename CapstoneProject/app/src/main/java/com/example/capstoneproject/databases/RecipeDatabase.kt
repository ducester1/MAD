package com.example.capstoneproject.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.capstoneproject.dao.RecipeDao
import com.example.capstoneproject.models.Converters
import com.example.capstoneproject.models.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [Recipe::class], version = 1)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {
        private const val DATABASE_NAME = "RECIPE_DATABASE"

        @Volatile
        private var INSTANCE: RecipeDatabase? = null

        fun getDatabase(context: Context): RecipeDatabase? {
            if(INSTANCE == null) {
                synchronized(RecipeDatabase::class) {
                    if(INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, RecipeDatabase::class.java, DATABASE_NAME)
                                .fallbackToDestructiveMigration()
                                .addCallback(object :RoomDatabase.Callback() {
                                    override fun onCreate(db: SupportSQLiteDatabase) {
                                        super.onCreate(db)
                                        INSTANCE?.let { database ->
                                            CoroutineScope(Dispatchers.IO).launch {
                                                //database.recipeDao().addRecipe(Recipe(null,"AVG", "Aardappelen Vlees Groenten", mutableListOf("Hollandse pot", "koolhydraten"), null,"koken", 1))
                                            }
                                        }
                                    }
                                })
                                .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}