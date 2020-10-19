package com.example.madlevel4task2.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.madlevel4task2.R
import com.example.madlevel4task2.model.Game
import com.example.madlevel4task2.model.GameMoves
import com.example.madlevel4task2.model.GameResults
import com.example.madlevel4task2.repository.GameRepository
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {
    private val mainScope = CoroutineScope(Dispatchers.Main)

    private lateinit var gameRepository: GameRepository

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameRepository = GameRepository(requireContext())
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

        mainScope.launch {
            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }
        }
        showResult(game)
    }

    private fun showResult(game: Game) {
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