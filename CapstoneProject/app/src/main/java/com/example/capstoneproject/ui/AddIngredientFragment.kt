package com.example.capstoneproject.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.capstoneproject.R
import com.example.capstoneproject.models.Ingredient
import com.example.capstoneproject.viewmodels.IngredientViewModel
import kotlinx.android.synthetic.main.fragment_add_ingredient.*
import java.util.*

class AddIngredientFragment : Fragment() {

    private lateinit var navController: NavController
    private val viewModel: IngredientViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_ingredient, container, false)
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
                navController.navigate(R.id.action_addIngredientFragment_to_warehouseFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initViews() {
        btn_add_ingredient.setOnClickListener{
            if (checkForm()) {
                viewModel.addIngredient(createIngredient())
                navController.popBackStack()
            } else {
                Toast.makeText(context, R.string.toast_fill_everything,Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createIngredient(): Ingredient {
        //Todo: val ingredientImage =
        val ingredientName = et_ingredient_name.text.toString()
        val ingredientDescription = et_ingredient_description.text.toString()
        val ingredientAmount = et_ingredient_amount.text.toString().toInt()
        val ingredientScale = et_ingredient_scale.text.toString()
        val ingredientExpireDate = Date((et_game_date_year.text.toString().toInt() - 1900),
                et_game_date_month.text.toString().toInt() - 1,
                et_game_date_day.text.toString().toInt())

        return Ingredient(null, ingredientName, ingredientDescription, ingredientAmount, ingredientScale, ingredientExpireDate)
    }

    private fun checkForm(): Boolean {
        when("") {
            et_ingredient_name.text.toString(),
            et_ingredient_description.text.toString(),
            et_ingredient_amount.text.toString(),
            et_ingredient_scale.text.toString(),
            et_game_date_day.text.toString(),
            et_game_date_month.text.toString(),
            et_game_date_year.text.toString()
            -> return false
        }
        return true
    }
}