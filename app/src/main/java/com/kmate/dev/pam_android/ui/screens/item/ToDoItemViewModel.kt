package com.kmate.dev.pam_android.ui.screens.item

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.kmate.dev.pam_android.data.local.LocalDataStore
import com.kmate.dev.pam_android.data.remote.ApiService
import com.kmate.dev.pam_android.domain.ToDoItem
import com.kmate.dev.pam_android.navigation.Route
import kotlinx.coroutines.launch

class ToDoItemViewModel(
    savedStateHandle: SavedStateHandle,
    localDataStore: LocalDataStore,
    apiService: ApiService,
): ViewModel() {
    var toDoItem by mutableStateOf<ToDoItem?>(null)
        private set

    init {
        val itemRoute = savedStateHandle.toRoute<Route.ToDoItemRoute>()
        val itemId = itemRoute.itemId
        if (itemId < 100) {
            viewModelScope.launch {
                apiService.getToDoItem(itemId)
                    .onSuccess {
                        toDoItem = it
                    }
                    .onFailure {
                        //Ignoring failure
                    }
            }
        } else {
            toDoItem = localDataStore.getToDoItem(itemId)
        }
    }
}