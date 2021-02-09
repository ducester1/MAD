package com.example.capstoneproject.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.children
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.capstoneproject.R
import com.example.capstoneproject.models.Recipe
import com.example.capstoneproject.viewmodels.RecipeViewModel
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.fragment_add_ingredient.btn_add_ingredient
import kotlinx.android.synthetic.main.fragment_add_recipe.*
import kotlinx.android.synthetic.main.item_recipe.view.*


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

        et_recipe_categories.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                println("enter pressed")
                val chip = Chip(context)
                chip.text = et_recipe_categories.text.toString()
                chip.setOnClickListener{
                    cg_categories.removeView(chip)
                }
                cg_categories.addView(chip)
                et_recipe_categories.setText("")
            }
            false
        })
    }

    private fun createRecipe(): Recipe {
        //Todo: val image =
        val name = et_recipe_name.text.toString()
        val description = et_recipe_description.text.toString()

        val categories = mutableListOf<String>()

        for (index in 0 until cg_categories.childCount) {
            val chip:Chip = cg_categories.getChildAt(index) as Chip
            categories.add(chip.text.toString())
        }
        //Todo: ingredients =
        val preparation = et_recipe_preparing.text.toString()

        return Recipe(null, name, description, categories, null, preparation)
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