package com.example.capstoneproject.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.adapters.IngredientAdapter
import com.example.capstoneproject.models.Ingredient
import kotlinx.android.synthetic.main.fragment_warehouse.*
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class WarehouseFragment : Fragment() {

    private val ingredients = arrayListOf<Ingredient>(
        Ingredient(null,"Aardappelen", "Gewoon", 5, "kg", Date(2019 - 1900,7,11), 1),
        Ingredient(null,"Aardappelen", "Gewoon", 5, "kg", Date(2019 - 1900,7,11), 2)
    )

    private lateinit var navController: NavController

    private val ingredientAdapter: IngredientAdapter = IngredientAdapter(ingredients)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_warehouse, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = findNavController()
        setHasOptionsMenu(true)
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.button_second).setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }

        initViews()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        activity?.title = "Warehouse"
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                navController.navigate(R.id.action_warehouseFragment_to_landingFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        rv_ingredients.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_ingredients.adapter = ingredientAdapter
    }
}