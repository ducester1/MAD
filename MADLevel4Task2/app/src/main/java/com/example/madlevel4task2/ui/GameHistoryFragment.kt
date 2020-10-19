package com.example.madlevel4task2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2.R
import com.example.madlevel4task2.model.Game
import com.example.madlevel4task2.model.GameMoves
import com.example.madlevel4task2.model.GameResults
import kotlinx.android.synthetic.main.fragment_game_history.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GameHistoryFragment : Fragment() {

    private val games: ArrayList<Game> = arrayListOf(
        Game(
        GameMoves.ROCK, GameMoves.SCISSOR, Calendar.getInstance().time,
        GameResults.LOSE
    ), Game(
        GameMoves.SCISSOR, GameMoves.ROCK, Calendar.getInstance().time,
        GameResults.WIN
    )
    )
    private lateinit var gamesAdapter: GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_game_history, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvHistory.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        rvHistory.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        gamesAdapter = GameAdapter(games)
        rvHistory.adapter = gamesAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                println("deleted")
                return true
            }
            android.R.id.home -> {
                println("back")
                activity?.onBackPressed()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}