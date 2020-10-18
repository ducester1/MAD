package com.example.madlevel4task1.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task1.R
import com.example.madlevel4task1.model.Product
import kotlinx.android.synthetic.main.fragment_shopping_list.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ShoppingListFragment : Fragment() {

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

        initRv()
    }

    private fun initRv() {
        rvShoppingList.layoutManager = LinearLayoutManager(this.requireContext(), RecyclerView.VERTICAL, false)
        rvShoppingList.adapter = shoppingListAdapter

        rvShoppingList.addItemDecoration(DividerItemDecoration(rvShoppingList.context, RecyclerView.VERTICAL))

//        for (i in Product.AMOUNT.indices) {
//            products.add(Product(Product.AMOUNT[i], Product.NAMES[i]))
//        }
//        shoppingListAdapter.notifyDataSetChanged()

        createItemTouchHelper().attachToRecyclerView(rvShoppingList)
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
                if (direction == ItemTouchHelper.LEFT || direction == ItemTouchHelper.RIGHT) {
                    products.removeAt(position)
                }
                shoppingListAdapter.notifyItemChanged(position)
                shoppingListAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }
}