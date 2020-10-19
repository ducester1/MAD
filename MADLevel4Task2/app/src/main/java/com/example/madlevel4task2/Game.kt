package com.example.madlevel4task2

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
class Game (
    var computerMove: GameMoves,
    var playerMove: GameMoves,
    var date: Date,
    var result: GameResults? = null
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