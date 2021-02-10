package com.example.capstoneproject.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.capstoneproject.R
import com.example.capstoneproject.models.Ingredient
import com.example.capstoneproject.viewmodels.IngredientViewModel
import kotlinx.android.synthetic.main.fragment_add_ingredient.*
import java.util.*
import androidx.lifecycle.Observer

class AddIngredientFragment : Fragment() {

    private lateinit var navController: NavController
    private val viewModel: IngredientViewModel by viewModels()

    val args: AddIngredientFragmentArgs by navArgs<AddIngredientFragmentArgs>()

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

    // Todo: Replace date
    private fun initViews() {
        val ingredientID = args.IngredientID
        if (ingredientID != -1L) {
            val ingredient = viewModel.getIngredient(ingredientID)
            ingredient.observe(viewLifecycleOwner, Observer { ingredient ->
                et_ingredient_name.setText(ingredient.name)
                et_ingredient_description.setText(ingredient.description)
                et_ingredient_amount.setText(ingredient.amount.toString())
                et_ingredient_scale.setText(ingredient.scale)
                val day = ingredient.expireDate.day
                et_expiration_date_day.setText(day.toString())
                val month = ingredient.expireDate.month + 1
                et_expiration_date_month.setText(month.toString())
                val year = ingredient.expireDate.year  +1900
                et_expiration_date_year.setText(year.toString())
            })
        }


        btn_add_ingredient.setOnClickListener{
            if (checkForm()) {
                if (ingredientID == -1L) {
                    viewModel.addIngredient(createIngredient())
                } else {
                    val ingredient = createIngredient()
                    ingredient.id = args.IngredientID
                    viewModel.updateIngredient(ingredient)
                }

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
        val ingredientExpireDate = Date((et_expiration_date_year.text.toString().toInt() - 1900),
                et_expiration_date_month.text.toString().toInt() - 1,
                et_expiration_date_day.text.toString().toInt())

        return Ingredient(null, ingredientName, ingredientDescription, ingredientAmount, ingredientScale, ingredientExpireDate)
    }

    private fun checkForm(): Boolean {
        when("") {
            et_ingredient_name.text.toString(),
            et_ingredient_description.text.toString(),
            et_ingredient_amount.text.toString(),
            et_ingredient_scale.text.toString(),
            et_expiration_date_day.text.toString(),
            et_expiration_date_month.text.toString(),
            et_expiration_date_year.text.toString()
            -> return false
        }
        return true
    }
}