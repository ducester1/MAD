package com.example.madlevel5task2.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.madlevel5task2.dao.GameDao
import com.example.madlevel5task2.database.GameBacklogDatabase
import com.example.madlevel5task2.models.Game

class GameRepository(context: Context) {
    private val gameDao: GameDao

    init {
        val database = GameBacklogDatabase.getDatabase(context)
        gameDao = database!!.gameBacklogDao()
    }

    fun getAllGames(): LiveData<List<Game>> {
        return gameDao.getAllGames()
    }

    suspend fun addGame(game: Game) {
        gameDao.addGame(game)
    }

    suspend fun deleteGame(game: Game) {
        gameDao.deleteGame(game)
    }

    suspend fun deleteAllGames() {
        gameDao.deleteAllGames()
    }

}