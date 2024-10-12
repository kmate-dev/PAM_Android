package com.kmate.dev.pam_android.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmate.dev.pam_android.domain.ToDoItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ToDoViewModel: ViewModel() {
    var isLoading by mutableStateOf(false)
    var todosList by mutableStateOf(emptyList<ToDoItem>())
        private set

    private var itemId = 0

    init {
        //Fake fetching notes from DB
        todosList += listOf(
            ToDoItem(
                id = getNextId(),
                name = "Prepare Android Example",
                completed = false
            ),
            ToDoItem(
                id = getNextId(),
                name = "Prepare iOS Example",
                completed = false
            )
        )
    }

    fun addToDoItem(name: String) {
        viewModelScope.launch {
            isLoading = true
            //Fake processing
            delay(500)
            todosList += ToDoItem(
                id = getNextId(),
                name = name,
                completed = false
            )
            isLoading = false
        }
    }

    fun completeItem(id: Int) {
        todosList.find { it.id == id }?.let { clickedItem ->
            todosList.toMutableList().let { newList ->
                newList.remove(clickedItem)
                newList.add(clickedItem.copy(completed = true))
                todosList = newList
            }
        }
    }

    private fun getNextId() = itemId++
}