package com.example.madlevel5task2.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.madlevel5task2.models.Game
import com.example.madlevel5task2.ui.GameBacklogFragment

@Dao
interface GameDao {

    @Query("SELECT * FROM GameBacklogTable")
    fun getAllGames(): LiveData<List<Game>>

    @Insert
    suspend fun addGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    @Query("DELETE FROM GameBacklogTable")
    suspend fun deleteAllGames()

}