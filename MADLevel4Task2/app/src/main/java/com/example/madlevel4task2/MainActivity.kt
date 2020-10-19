package com.example.madlevel4task2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (menu != null) {
            toggleActionBar(menu)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_game_history -> {
                navController.navigate(R.id.action_gameFragment_to_gameHistoryFragment)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun toggleActionBar(menu: Menu){
        val gameHistoryButton = menu.findItem(R.id.action_game_history)
        val deleteHistoryButton = menu.findItem(R.id.action_delete)
        navController.addOnDestinationChangedListener{ _, destination, _ ->
            if (destination.id in arrayOf(R.id.gameHistoryFragment)) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                gameHistoryButton.isVisible = false
                deleteHistoryButton.isVisible = true
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                gameHistoryButton.isVisible = true
                deleteHistoryButton.isVisible = false
            }
        }
    }
}