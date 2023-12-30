package com.example.shoppinglist.ui.shoppingList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.R
import com.example.shoppinglist.data.database.entities.ShoppingItem
import com.example.shoppinglist.databinding.DialogAdditemBinding



class AddShoppingItemDialog(context: Context, private val addDialogListener: AddDialogListener) : AppCompatDialog(context) {
    private lateinit var binding: DialogAdditemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogAdditemBinding.inflate(LayoutInflater.from(context))
        setContentView(binding.root)
        setContentView(R.layout.shopping_items)

        binding.tvAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val amount = binding.etAmount.text.toString()
            if (name.isEmpty() || amount.isEmpty()) {
                Toast.makeText(context, "Please enter all information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Additional validation can be added here, e.g., checking if amount is a valid number

            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }

        binding.tvCancel.setOnClickListener {
            cancel()
        }
    }
}
