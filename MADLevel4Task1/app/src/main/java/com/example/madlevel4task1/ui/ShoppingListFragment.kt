package com.example.madlevel4task1.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task1.R
import com.example.madlevel4task1.model.Product
import com.example.madlevel4task1.repository.ProductRepository
import kotlinx.android.synthetic.main.fragment_shopping_list.*
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ShoppingListFragment : Fragment() {

    private lateinit var productRepository: ProductRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    private val products = arrayListOf<Product>()
    private val shoppingListAdapter = ShoppingListAdapter(products)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productRepository = ProductRepository(requireContext())
        getShoppingListFromDatabase()

        initRv()

        fabAdd.setOnClickListener {
            showAddProductdialog()
        }

        fabDelete.setOnClickListener {
            removeAllProducts()
        }
    }

    private fun initRv() {
        rvShoppingList.layoutManager = LinearLayoutManager(this.requireContext(), RecyclerView.VERTICAL, false)
        rvShoppingList.adapter = shoppingListAdapter
        rvShoppingList.addItemDecoration(DividerItemDecoration(rvShoppingList.context, RecyclerView.VERTICAL))

        createItemTouchHelper().attachToRecyclerView(rvShoppingList)
    }

    private fun getShoppingListFromDatabase() {
        MainScope().launch {
            val shoppingList = withContext(Dispatchers.IO) {
                productRepository.getAllProducts()
            }
            this@ShoppingListFragment.products.clear()

            this@ShoppingListFragment.products.addAll(shoppingList)
            this@ShoppingListFragment.shoppingListAdapter.notifyDataSetChanged()
        }
    }

    private fun showAddProductdialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(getString(R.string.add_product_dialog_title))
        val dialogLayout = layoutInflater.inflate(R.layout.add_product_dialog, null)
        val productName = dialogLayout.findViewById<EditText>(R.id.txt_product_name)
        val amount = dialogLayout.findViewById<EditText>(R.id.txt_amount)

        builder.setView(dialogLayout)
        builder.setPositiveButton(R.string.dialog_ok_btn) { _: DialogInterface, _: Int -> addProduct(productName, amount)}

        builder.show()
    }

    private fun addProduct(txtProductName: EditText, txtAmount: EditText) {
        if (validateFields(txtProductName, txtAmount)) {
            MainScope().launch {
                val product = Product(
                    productName = txtProductName.text.toString(),
                    productAmount = txtAmount.text.toString().toInt()
                )

                withContext(Dispatchers.IO) {
                    productRepository.insertProduct(product)
                }

                getShoppingListFromDatabase()
            }
        }
    }

    private fun validateFields(txtProductName: EditText, txtAmount: EditText) : Boolean {
        return if (txtProductName.text.toString().isNotBlank() && txtAmount.text.toString().isNotBlank()) {
            true
        } else {
            Toast.makeText(activity, "Please fill in the fields", Toast.LENGTH_LONG).show()
            false
        }
    }

    private fun removeAllProducts() {
        MainScope().launch {
            withContext(Dispatchers.IO) {
                productRepository.deleteAllProducts()
            }
            getShoppingListFromDatabase()
        }
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val productToDelete = products[position]
                MainScope().launch {
                    withContext(Dispatchers.IO) {
                        productRepository.deleteProduct(productToDelete)
                    }
                    getShoppingListFromDatabase()
                }
            }
        }
        return ItemTouchHelper(callback)
    }
}