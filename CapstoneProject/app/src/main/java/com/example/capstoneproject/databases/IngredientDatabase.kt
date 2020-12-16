package com.example.capstoneproject.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.capstoneproject.dao.IngredientDao
import com.example.capstoneproject.models.Converters
import com.example.capstoneproject.models.Ingredient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [Ingredient::class], version = 1)
@TypeConverters(Converters::class)
abstract class IngredientDatabase : RoomDatabase() {

    abstract fun ingredientDao(): IngredientDao

    companion object {
        private const val DATABASE_NAME = "GAME_BACKLOG_DATABASE"

        @Volatile
        private var INSTANCE: IngredientDatabase? = null

        fun getDatabase(context: Context): IngredientDatabase? {
            if(INSTANCE == null) {
                synchronized(IngredientDatabase::class) {
                    if(INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, IngredientDatabase::class.java, DATABASE_NAME)
                                .fallbackToDestructiveMigration()
                                .addCallback(object :RoomDatabase.Callback() {
                                    override fun onCreate(db: SupportSQLiteDatabase) {
                                        super.onCreate(db)
                                        INSTANCE?.let { database ->
                                            CoroutineScope(Dispatchers.IO).launch {
                                                database.ingredientDao().addIngredient(Ingredient(null,"Aardappelen", "Gewoon", 5, "kg", Date(2019 - 1900,7,11), 1))
                                                database.ingredientDao().addIngredient(Ingredient(null,"koek", "lekker", 500, "gram", Date(2019 - 1900,7,11), 2))
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