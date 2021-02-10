package com.example.capstoneproject.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.adapters.IngredientAdapter
import com.example.capstoneproject.models.Ingredient
import com.example.capstoneproject.viewmodels.IngredientViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_warehouse.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class WarehouseFragment : Fragment() {

    private lateinit var navController: NavController
    private val viewModel: IngredientViewModel by viewModels()

    private val ingredients = arrayListOf<Ingredient>()
    private val ingredientAdapter: IngredientAdapter = IngredientAdapter(ingredients)

    private val args: WarehouseFragmentArgs by navArgs<WarehouseFragmentArgs>()
    private var isRecipeIngredient = false

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

        if (args.RecipeID != -1L) isRecipeIngredient = true

        view.findViewById<FloatingActionButton>(R.id.FAB_add_ingredient).setOnClickListener {
            findNavController().navigate(R.id.action_warehouseFragment_to_addIngredientFragment)
        }

        initViews()
        observeAddIngredientResult()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        activity?.title = "Warehouse"
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                navController.navigate(R.id.action_warehouseFragment_to_landingFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        rv_ingredients.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_ingredients.adapter = ingredientAdapter

        ingredientAdapter.onItemClick = { ingredient ->
            if (findNavController().currentDestination!!.id == R.id.warehouseFragment) {
                if (isRecipeIngredient) {
                    val action = ingredient.id?.let {
                        WarehouseFragmentDirections.actionWarehouseFragmentToAddRecipeFragment(args.RecipeID,
                            it
                        )

                    }
                    if (action != null) {
                        findNavController().navigate(action)
                    }
                } else {
                    val action =
                        WarehouseFragmentDirections.actionWarehouseFragmentToAddIngredientFragment(
                            ingredient.id!!
                        )
                    findNavController().navigate(action)
                }
            }
        }
        createItemTouchHelper().attachToRecyclerView(rv_ingredients)
    }

    private fun observeAddIngredientResult() {
        viewModel.ingredients.observe(viewLifecycleOwner, Observer { ingredients ->
            this@WarehouseFragment.ingredients.clear()
            this@WarehouseFragment.ingredients.addAll(ingredients)

            this.ingredientAdapter.notifyDataSetChanged()
        })
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        var callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val ingredientToDelete: Ingredient = ingredients[position]
                viewModel.deleteIngredient(ingredientToDelete)
            }
        }
        return ItemTouchHelper(callback)
    }
}