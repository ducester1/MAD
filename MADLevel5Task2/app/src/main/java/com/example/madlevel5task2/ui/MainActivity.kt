package com.example.madlevel5task2.ui

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.madlevel5task2.R

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        navController = findNavController(R.id.nav_host_fragment)
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            navController.navigate(R.id.action_GameBacklogFragment_to_AddGameFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        navController.addOnDestinationChangedListener {
                _, destination, _ ->
            if(destination.id == R.id.GameBacklogFragment) {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            } else if(destination.id == R.id.AddGameFragment) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}