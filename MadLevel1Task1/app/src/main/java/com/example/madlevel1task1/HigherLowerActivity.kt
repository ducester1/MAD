package com.example.madlevel1task1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.madlevel1task1.databinding.ActivityHigherLowerBinding

class HigherLowerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHigherLowerBinding
    private var currentThrow: Int = 1
    private var lastThrow: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHigherLowerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    fun initViews() {
        binding.btnLower.setOnClickListener { onLowerClick() }
        binding.btnEquals.setOnClickListener { onEqualClick() }
        binding.btnHigher.setOnClickListener { onHigherClick() }
        updateUI()
    }

    fun updateUI() {
        binding.tvLastThrow.text = getString(R.string.last_throw, lastThrow)

        when(currentThrow){
            1 -> binding.imageView.setImageResource(R.drawable.dice1)
            2 -> binding.imageView.setImageResource(R.drawable.dice2)
            3 -> binding.imageView.setImageResource(R.drawable.dice3)
            4 -> binding.imageView.setImageResource(R.drawable.dice4)
            5 -> binding.imageView.setImageResource(R.drawable.dice5)
            6 -> binding.imageView.setImageResource(R.drawable.dice6)
        }
    }

    fun rollDice() {
        lastThrow = currentThrow;
        currentThrow = (1..6).random()
        updateUI()
    }
    
    private fun onAnswerCorrect() {
        Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show()
    }
    
    private fun onAnswerIncorrect() {
        Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show()
    }

    private fun onHigherClick() {
        rollDice()
        if (currentThrow > lastThrow) onAnswerCorrect()
        else onAnswerIncorrect()
    }

    private fun onLowerClick() {
        rollDice()
        if (currentThrow < lastThrow) onAnswerCorrect()
        else onAnswerIncorrect()
    }

    private fun onEqualClick() {
        rollDice()
        if (currentThrow == lastThrow) onAnswerCorrect()
        else onAnswerIncorrect()
    }
}
