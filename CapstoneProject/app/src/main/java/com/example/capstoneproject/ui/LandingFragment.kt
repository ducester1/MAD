package com.example.capstoneproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.example.capstoneproject.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LandingFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_landing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<CardView>(R.id.cv_warehouse).setOnClickListener{
            findNavController().navigate(R.id.action_landingFragment_to_warehouseFragment)
        }

        view.findViewById<Button>(R.id.btnWhatCanUMake).setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        activity?.title = getString(R.string.landing_fragment_title)
        super.onPrepareOptionsMenu(menu)
    }
}