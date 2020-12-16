package com.example.capstoneproject.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.adapters.RecipeAdapter
import com.example.capstoneproject.models.Recipe
import com.example.capstoneproject.viewmodels.RecipeViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_recipes.*
import kotlinx.android.synthetic.main.fragment_warehouse.*

class RecipesFragment : Fragment() {
    private lateinit var navController: NavController
    private val viewModel: RecipeViewModel by viewModels()

    private val recipes = arrayListOf<Recipe>()
    private val recipeAdapter: RecipeAdapter = RecipeAdapter(recipes)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = findNavController()
        setHasOptionsMenu(true)
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<FloatingActionButton>(R.id.FAB_add_recipe).setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_addRecipeFragment)
        }

        initViews()
        observeAddRecipeResults()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        activity?.title = "Recipes"
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                navController.navigate(R.id.action_recipesFragment_to_landingFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        rv_recipes.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_recipes.adapter = recipeAdapter
        createItemTouchHelper().attachToRecyclerView(rv_recipes)
    }

    private fun observeAddRecipeResults() {
        viewModel.recipes.observe(viewLifecycleOwner, Observer{ recipes ->
            this@RecipesFragment.recipes.clear()
            this@RecipesFragment.recipes.addAll(recipes)

            this.recipeAdapter.notifyDataSetChanged()
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
                val ingredientToDelete: Recipe = recipes[position]
                viewModel.deleteRecipe(ingredientToDelete)
            }
        }
        return ItemTouchHelper(callback)
    }
}