package com.example.capstoneproject.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.R
import com.example.capstoneproject.adapters.IngredientAdapter
import com.example.capstoneproject.models.Ingredient
import com.example.capstoneproject.models.Recipe
import com.example.capstoneproject.viewmodels.IngredientViewModel
import com.example.capstoneproject.viewmodels.RecipeViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_add_ingredient.btn_add_ingredient
import kotlinx.android.synthetic.main.fragment_add_recipe.*


class AddRecipeFragment: Fragment() {

    private lateinit var navController: NavController
    private val viewModel: RecipeViewModel by viewModels()
    private val ingredientViewModel: IngredientViewModel by viewModels()

    private val ingredients = arrayListOf<Ingredient>()
    private val ingredientAdapter: IngredientAdapter = IngredientAdapter(ingredients)

    val args: AddRecipeFragmentArgs by navArgs<AddRecipeFragmentArgs>()

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

        view.findViewById<FloatingActionButton>(R.id.FAB_add_ingredient_to_recipe).setOnClickListener {
            if (checkForm()) {
                if(args.RecipeID == -1L) {
                    viewModel.addRecipe(createRecipe())
                } else {
                    val recipe = createRecipe()
                    recipe.id = args.RecipeID
                    viewModel.updateRecipe(recipe)
                }
                val action = AddRecipeFragmentDirections.actionAddRecipeFragmentToWarehouseFragment(args.RecipeID)
                findNavController().navigate(action)
            } else {
                Toast.makeText(context, R.string.toast_fill_everything, Toast.LENGTH_SHORT).show()
            }
        }

        //observeAddIngredientResult()
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
        rv_recipe_ingredients.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rv_recipe_ingredients.adapter = ingredientAdapter
        createItemTouchHelper().attachToRecyclerView(rv_recipe_ingredients)

        val recipeID = args.RecipeID
        if (recipeID != -1L) {
            val recipe = viewModel.getRecipe(recipeID)
            recipe.observe(viewLifecycleOwner, Observer { recipe ->
                et_recipe_name.setText(recipe.name.toString())
                et_recipe_description.setText(recipe.description.toString())

                for (index in 0 until recipe.categories?.count()!!) {
                    val chip = Chip(context)
                    chip.text = recipe.categories!![index].toString()
                    chip.setOnClickListener{
                        cg_categories.removeView(chip)
                    }
                    cg_categories.addView(chip)
                }

                if (recipe.ingredientsId != null) {
                    if (args.IngredientIDs != -1L) {
                        recipe.ingredientsId!!.add(args.IngredientIDs)
                    }

                    for (index in 0 until recipe.ingredientsId?.count()!!) {
                        val ingredient =
                            ingredientViewModel.getIngredient(recipe.ingredientsId!![index])

                        ingredient.observe(viewLifecycleOwner, Observer { ingredient ->
                            this@AddRecipeFragment.ingredients.add(ingredient)
                            this.ingredientAdapter.notifyDataSetChanged()
                        })
                    }
                }

                et_recipe_preparing.setText(recipe.preperation.toString())
            })
        }

        btn_add_ingredient.setOnClickListener{
            if (checkForm()) {
                if(recipeID == -1L) {
                    viewModel.addRecipe(createRecipe())
                } else {
                    val recipe = createRecipe()
                    recipe.id = args.RecipeID
                    viewModel.updateRecipe(recipe)
                }
                navController.navigate(R.id.action_addRecipeFragment_to_recipesFragment)
            } else {
                Toast.makeText(context, R.string.toast_fill_everything, Toast.LENGTH_SHORT).show()
            }
        }

        et_recipe_categories.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if ((keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_TAB) && event.action == KeyEvent.ACTION_UP) {
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

    private fun observeAddIngredientResult() {
        ingredientViewModel.ingredients.observe(viewLifecycleOwner, Observer{ ingredients ->
            this@AddRecipeFragment.ingredients.clear()
            this@AddRecipeFragment.ingredients.addAll(ingredients)

            this.ingredientAdapter.notifyDataSetChanged()
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

        val ingredients = mutableListOf<Long>()
        for (index in 0 until this.ingredients.size) {
            this.ingredients[index].id?.let { ingredients.add(it) }
        }
        //val setIngredients = mutableListOf<Long>(2L, 3L)
        val preparation = et_recipe_preparing.text.toString()
        return Recipe(null, name, description, categories, ingredients, preparation)
    }

    //Check if filled in properly
    private fun checkForm(): Boolean {
        when("") {
            et_recipe_name.text.toString()
            -> return false
        }
        return true
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
                val ingredientToDelete: Ingredient = ingredients.removeAt(position)
                ingredientAdapter.notifyDataSetChanged()
                println(ingredients)
            }
        }
        return ItemTouchHelper(callback)
    }
}