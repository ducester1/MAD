package com.example.madlevel5task2.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.madlevel5task2.R
import com.example.madlevel5task2.models.Game
import com.example.madlevel5task2.viewmodels.GameViewModel
import kotlinx.android.synthetic.main.fragment_add_game.*
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AddGameFragment : Fragment() {

    private lateinit var navController: NavController
    private val viewModel: GameViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = findNavController()
        setHasOptionsMenu(true)
        initViews()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        activity?.title = "Add Game"
        menu.removeItem(R.id.clear_game_backlog)
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                navController.navigate(R.id.action_AddGameFragment_to_GameBacklogFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        fab_save_game.setOnClickListener {
            viewModel.addGame(createGame())
            navController.popBackStack()
        }
    }

    private fun createGame(): Game {
        val gameTitle = et_game_title.text.toString()
        val gamePlatforms = et_game_platforms.text.toString()
        val gameDate = Date((et_game_date_year.text.toString().toInt()-1900),
        et_game_date_month.text.toString().toInt()-1,
        et_game_date_day.text.toString().toInt())
        return Game(gameTitle,gamePlatforms,gameDate)
    }
}