package com.kmate.dev.pam_android.data.remote

import com.kmate.dev.pam_android.domain.ToDoItem

interface ApiService {
    suspend fun getToDoItems(): Result<List<ToDoItem>>
    suspend fun getToDoItem(itemId: Int): Result<ToDoItem?>
}