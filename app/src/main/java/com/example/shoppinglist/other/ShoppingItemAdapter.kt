package com.example.shoppinglist.other

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.data.database.entities.ShoppingItem
import com.example.shoppinglist.databinding.ShoppingItemsBinding
import com.example.shoppinglist.ui.shoppingList.ShoppingViewModel

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        // Inflate using View Binding
        val binding = ShoppingItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val curShoppingItem = items[position]
        with(holder.binding) {
            tvName.text = curShoppingItem.name
            tvAmount.text = "${curShoppingItem.amount}"

            ivDelete.setOnClickListener {
                viewModel.delete(curShoppingItem)
            }

            ivPlus.setOnClickListener {
                curShoppingItem.amount++
                viewModel.upsert(curShoppingItem)
            }

            ivMinus.setOnClickListener {
                if (curShoppingItem.amount > 0) {
                    curShoppingItem.amount--
                    viewModel.upsert(curShoppingItem)
                }
            }
        }
    }

    inner class ShoppingViewHolder(val binding: ShoppingItemsBinding) : RecyclerView.ViewHolder(binding.root)
}
