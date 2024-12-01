package com.kmate.dev.pam_android.data.remote

import android.util.Log
import com.kmate.dev.pam_android.domain.DummyJsonItems
import com.kmate.dev.pam_android.domain.DummyJsonToDoItem
import com.kmate.dev.pam_android.domain.ToDoItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiServiceImpl(
    private val client: HttpClient
): ApiService {
    override suspend fun getToDoItems(): Result<List<ToDoItem>> {
        return try {
            val response: DummyJsonItems = client.get("https://dummyjson.com/todos") {

            }.body()
            val toDoItems = response.todos.map {
                ToDoItem(
                    id = it.id,
                    name = it.todo,
                    completed = it.completed
                )
            }
            Result.success(toDoItems)
        } catch (e: Exception) {
            Log.e("API", e.message ?: "API error")
            Result.failure(e)
        }
    }

    override suspend fun getToDoItem(itemId: Int): Result<ToDoItem?> {
        return try {
            val response: DummyJsonToDoItem = client.get("https://dummyjson.com/todos/$itemId") {}
                .body()
            val toDoItem = ToDoItem(
                id = response.id,
                name = response.todo,
                completed = response.completed,
            )
            Result.success(toDoItem)
        } catch (e: Exception) {
            Log.e("API", e.message ?: "API error")
            Result.failure(e)
        }
    }

}