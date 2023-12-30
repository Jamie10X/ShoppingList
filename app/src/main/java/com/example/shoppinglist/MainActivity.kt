package com.example.shoppinglist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.data.database.ShoppingDatabase
import com.example.shoppinglist.data.database.entities.ShoppingItem
import com.example.shoppinglist.data.repositories.ShoppingRepository
import com.example.shoppinglist.databinding.ActivityShoppingBinding
import com.example.shoppinglist.other.ShoppingItemAdapter
import com.example.shoppinglist.ui.shoppingList.AddDialogListener
import com.example.shoppinglist.ui.shoppingList.AddShoppingItemDialog
import com.example.shoppinglist.ui.shoppingList.ShoppingViewModel
import com.example.shoppinglist.ui.shoppingList.ShoppingViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingBinding

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)

        val viewModel = ViewModelProvider(this, factory)[ShoppingViewModel::class.java]

        val adapter = ShoppingItemAdapter(listOf(), viewModel)
        binding.shoppingitems.layoutManager = LinearLayoutManager(this)
        binding.shoppingitems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this) {
            adapter.items = it
            adapter.notifyDataSetChanged()
        }
        binding.flobtn.setOnClickListener{
            AddShoppingItemDialog(this,
        object : AddDialogListener {
            override fun onAddButtonClicked(item: ShoppingItem) {
            viewModel.upsert(item)
            }
        })
                .show()
        }}
    }