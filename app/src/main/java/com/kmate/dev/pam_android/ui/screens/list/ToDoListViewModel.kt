package com.kmate.dev.pam_android.ui.screens.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kmate.dev.pam_android.data.local.LocalDataStore
import com.kmate.dev.pam_android.data.remote.ApiService
import com.kmate.dev.pam_android.domain.ToDoItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ToDoListViewModel(
    private val localDataStore: LocalDataStore,
    private val apiService: ApiService,
): ViewModel() {
    var isLoading by mutableStateOf(false)
        private set
    var todosList by mutableStateOf(emptyList<ToDoItem>())
        private set

    init {
        //Fake fetching notes from DB
        todosList += localDataStore.getToDoItems()
        viewModelScope.launch {
            apiService.getToDoItems()
                .onSuccess {
                    todosList += it
                }
                .onFailure {
                    // Ignoring failures
                }
        }
    }

    fun addToDoItem(name: String) {
        viewModelScope.launch {
            isLoading = true
            //Fake processing
            delay(500)
            //Starting from > 30 to avoid local and network items mixing (dirty workaround)
            val newId = todosList.maxByOrNull { it.id }?.id?.plus(1) ?: 100
            todosList += ToDoItem(
                id = newId,
                name = name,
                completed = false
            )

            localDataStore.updateToDoItems(todosList)
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
        localDataStore.updateToDoItems(todosList)
    }
}