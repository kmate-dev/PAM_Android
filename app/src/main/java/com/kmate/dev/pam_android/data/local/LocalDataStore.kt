package com.kmate.dev.pam_android.data.local

import com.kmate.dev.pam_android.domain.ToDoItem

interface LocalDataStore {
    fun updateToDoItems(items: List<ToDoItem>)
    fun getToDoItems(): List<ToDoItem>
    fun getToDoItem(itemId: Int): ToDoItem?
}