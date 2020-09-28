package com.example.madlevel1task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.madlevel1task2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSubmit.setOnClickListener { checkAnswers() }
    }

    private fun checkAnswers() {
        var nrCorrect = 0
        if (binding.ptC1.text.toString() == getString(R.string.tv_T)) nrCorrect += 1
        else if(binding.ptC1.text.toString() == getString(R.string.tv_F))
        else return Toast.makeText(this, getString(R.string.t_or_f), Toast.LENGTH_SHORT).show()

        if (binding.ptC2.text.toString() == getString(R.string.tv_F) )nrCorrect += 1
        else if(binding.ptC2.text.toString() == getString(R.string.tv_T))
        else return Toast.makeText(this, getString(R.string.t_or_f), Toast.LENGTH_SHORT).show()

        if (binding.ptC3.text.toString() == getString(R.string.tv_F)) nrCorrect += 1
        else if(binding.ptC3.text.toString() == getString(R.string.tv_T))
        else return Toast.makeText(this, getString(R.string.t_or_f), Toast.LENGTH_SHORT).show()

        if (binding.ptC4.text.toString() == getString(R.string.tv_F)) nrCorrect += 1
        else if(binding.ptC4.text.toString() == getString(R.string.tv_T))
        else return Toast.makeText(this, getString(R.string.t_or_f), Toast.LENGTH_SHORT).show()

        Toast.makeText(this, getString(R.string.correct, nrCorrect), Toast.LENGTH_SHORT).show()
    }
}