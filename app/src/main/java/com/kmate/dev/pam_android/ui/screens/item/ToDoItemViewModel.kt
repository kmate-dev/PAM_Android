package com.kmate.dev.pam_android.ui.screens.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import com.kmate.dev.pam_android.data.local.LocalDataStore
import com.kmate.dev.pam_android.domain.ToDoItem
import com.kmate.dev.pam_android.navigation.Route

class ToDoItemViewModel(
    savedStateHandle: SavedStateHandle,
    localDataStore: LocalDataStore
): ViewModel() {
    var toDoItem by mutableStateOf<ToDoItem?>(null)
        private set

    init {
        val itemRoute = savedStateHandle.toRoute<Route.ToDoItemRoute>()
        val itemId = itemRoute.itemId
        toDoItem = localDataStore.getToDoItem(itemId)
    }
}