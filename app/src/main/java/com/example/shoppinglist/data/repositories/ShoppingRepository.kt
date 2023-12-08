package com.example.shoppinglist.data.repositories

import com.example.shoppinglist.data.database.ShoppingDatabase
import com.example.shoppinglist.data.database.entities.ShoppingItem

class ShoppingRepository (
    private val db:ShoppingDatabase
) {
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().Upsort(item)

    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().Delete(item)

    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItems()
}