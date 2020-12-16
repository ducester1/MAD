package com.example.capstoneproject.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.capstoneproject.R
import com.example.capstoneproject.models.Recipe
import com.example.capstoneproject.viewmodels.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_add_ingredient.*
import kotlinx.android.synthetic.main.fragment_add_ingredient.btn_add_ingredient
import kotlinx.android.synthetic.main.fragment_add_recipe.*
import java.util.*

class AddRecipeFragment: Fragment() {

    private lateinit var navController: NavController
    private val viewModel: RecipeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_recipe, container, false)
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
        activity?.title = "Add ingredient"
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                navController.navigate(R.id.action_addRecipeFragment_to_recipesFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        btn_add_ingredient.setOnClickListener{
            if (checkForm()) {
                viewModel.addRecipe(createRecipe())
                navController.popBackStack()
            } else {
                Toast.makeText(context, R.string.toast_fill_everything, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createRecipe(): Recipe {
        //Todo: val image =
        val name = et_recipe_name.text.toString()
        val description = et_recipe_description.text.toString()
        //Todo: val categories =
        //Todo: ingredients =
        val preperation = et_recipe_preparing.text.toString()

        return Recipe(null, name, description, null, null, preperation)
    }

    //Check if n
    private fun checkForm(): Boolean {
        when("") {
            et_recipe_name.text.toString()
            -> return false
        }
        return true
    }
}