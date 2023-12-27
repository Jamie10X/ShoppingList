package com.example.shoppinglist.ui.shoppingList

import com.example.shoppinglist.data.database.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}