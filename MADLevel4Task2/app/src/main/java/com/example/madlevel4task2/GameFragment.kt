package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_game.*
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        ibRock.setOnClickListener {
            playGame(GameMoves.ROCK)
        }

        ibPaper.setOnClickListener {
            playGame(GameMoves.PAPER)
        }

        ibScissors.setOnClickListener {
            playGame(GameMoves.SCISSOR)
        }
    }

    private fun playGame(playerMove: GameMoves) {
        val computerMove = GameMoves.values()[(0..2).random()]
        val game = Game(computerMove, playerMove, Calendar.getInstance().time)
        game.calculateResults()
        showResult(game)
    }

    private fun showResult(game: Game) {
        println(game.date)
        when(game.computerMove) {
            GameMoves.ROCK -> ivComputer.setImageResource(R.drawable.rock)
            GameMoves.PAPER -> ivComputer.setImageResource(R.drawable.paper)
            GameMoves.SCISSOR -> ivComputer.setImageResource(R.drawable.scissors)
        }

        when(game.playerMove) {
            GameMoves.ROCK -> ivPlayer.setImageResource(R.drawable.rock)
            GameMoves.PAPER -> ivPlayer.setImageResource(R.drawable.paper)
            GameMoves.SCISSOR -> ivPlayer.setImageResource(R.drawable.scissors)
        }

        when(game.result) {
            GameResults.WIN -> tvResult.setText(R.string.win)
            GameResults.LOSE -> tvResult.setText(R.string.lose)
            GameResults.DRAW -> tvResult.setText(R.string.draw)
        }
    }
}