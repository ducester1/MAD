package com.example.madlevel5task2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.madlevel5task2.dao.GameDao
import com.example.madlevel5task2.models.Converters
import com.example.madlevel5task2.models.Game

@Database(entities = [Game::class], version = 1)
@TypeConverters(Converters::class)
abstract class GameBacklogDatabase : RoomDatabase() {

    abstract fun gameBacklogDao(): GameDao

    companion object {
        private const val DATABASE_NAME = "GAME_BACKLOG_DATABASE"

        @Volatile
        private var INSTANCE: GameBacklogDatabase? = null

        fun getDatabase(context: Context): GameBacklogDatabase? {
            if(INSTANCE == null) {
                synchronized(GameBacklogDatabase::class) {
                    if(INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            GameBacklogDatabase::class.java,
                            DATABASE_NAME
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }
}