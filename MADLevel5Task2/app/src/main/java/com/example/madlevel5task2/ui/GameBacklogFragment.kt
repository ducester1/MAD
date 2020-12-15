package com.example.madlevel5task2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel5task2.R
import com.example.madlevel5task2.adapaters.GameAdapter
import com.example.madlevel5task2.models.Game
import kotlinx.android.synthetic.main.fragment_game_backlog.*
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class GameBacklogFragment : Fragment() {

    private val games = arrayListOf<Game>(
        Game("Doom Eternal", "PC, Playstation 4", Date(2019 - 1900,7,11)),
        Game("Call of Duty", "Xbox One, Playstation 4", Date(2019 - 1900,11,10)),
        Game("Borderlands 3", "PC, PS4", Date(2019 - 1900,11,4))
    )
    private val gameAdapter: GameAdapter = GameAdapter(games)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_backlog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        activity?.title = "Game Backlog"
        super.onPrepareOptionsMenu(menu)
    }

    private fun initViews() {
        rvGames.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvGames.adapter = gameAdapter
    }
}