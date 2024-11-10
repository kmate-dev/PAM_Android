package com.kmate.dev.pam_android.data.local

import android.content.SharedPreferences
import com.kmate.dev.pam_android.domain.ToDoItem
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

interface LocalDataStore {
    fun updateToDoItems(items: List<ToDoItem>)
    fun getToDoItems(): List<ToDoItem>
    fun getToDoItem(itemId: Int): ToDoItem?
}

class LocalDataStoreImpl(private val sharedPreferences: SharedPreferences): LocalDataStore {
    companion object {
        private const val TO_DO_ITEMS_KEY = "to_do_items_key"
    }

    override fun getToDoItems(): List<ToDoItem> {
        try {
            val toDoItemsListString = sharedPreferences.getString(
                TO_DO_ITEMS_KEY,
                ""
            )
            val toDoItemsList = Json.decodeFromString<List<ToDoItem>>(toDoItemsListString!!)
            return toDoItemsList
        } catch (e: Exception) {
            // Ignoring potential errors and returning empty list
            return emptyList()
        }
    }

    override fun updateToDoItems(items: List<ToDoItem>) {
        try {
            sharedPreferences.edit().putString(
                TO_DO_ITEMS_KEY,
                Json.encodeToString(items)
            ).apply()
        } catch (e: Exception) {
            // Ignoring potential errors as this is just example app
        }
    }

    override fun getToDoItem(itemId: Int): ToDoItem? {
        val toDoItemsList = getToDoItems()
        return toDoItemsList.find { it.id == itemId }
    }
}