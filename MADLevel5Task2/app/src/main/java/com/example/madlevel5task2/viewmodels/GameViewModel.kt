package com.example.madlevel5task2.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.madlevel5task2.models.Game
import com.example.madlevel5task2.repository.GameRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private var mainScope = CoroutineScope(Dispatchers.Main)
    private val gameRepository: GameRepository = GameRepository(application.applicationContext)
    val games = gameRepository.getAllGames()

    fun addGame(game: Game) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.addGame(game)
            }
        }
    }

    fun deleteGame(game: Game) {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteGame(game)
            }
        }
    }

    fun deleteAllGames() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.deleteAllGames()
            }
        }
    }
} 