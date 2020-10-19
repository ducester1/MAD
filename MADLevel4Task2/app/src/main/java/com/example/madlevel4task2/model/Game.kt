package com.example.madlevel4task2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

enum class GameMoves {
    ROCK,
    PAPER,
    SCISSOR
}

enum class GameResults {
    WIN,
    LOSE,
    DRAW
}

@Entity(tableName = "game_history_table")
class Game (
    @ColumnInfo(name = "computer_move")
    var computerMove: GameMoves,
    @ColumnInfo(name = "player_move")
    var playerMove: GameMoves,
    @ColumnInfo(name = "date")
    var date: Date,
    @ColumnInfo(name = "result")
    var result: GameResults? = null,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

) {
    fun calculateResults() {
        if  (playerMove == computerMove) {
            result = GameResults.DRAW
        } else {
            if (playerMove == GameMoves.ROCK) {
                if (computerMove == GameMoves.PAPER) {
                    result = GameResults.LOSE
                } else if (computerMove == GameMoves.SCISSOR) {
                    result = GameResults.WIN
                }
            } else if (playerMove == GameMoves.PAPER) {
                if (computerMove == GameMoves.SCISSOR) {
                    result = GameResults.LOSE
                } else if (computerMove == GameMoves.ROCK) {
                    result = GameResults.WIN
                }
            } else if (playerMove == GameMoves.SCISSOR) {
                if (computerMove == GameMoves.ROCK) {
                    result = GameResults.LOSE
                } else if (computerMove == GameMoves.PAPER) {
                    result = GameResults.WIN
                }
            }
        }
    }
}