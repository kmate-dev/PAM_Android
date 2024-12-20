package com.kmate.dev.pam_android.ui.screens.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kmate.dev.pam_android.domain.ToDoItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun ToDoItemScreen(
    viewModel: ToDoItemViewModel = koinViewModel()
) {
    ToDoItemScreen(
        viewModel.toDoItem
    )
}

@Composable
fun ToDoItemScreen(
    item: ToDoItem?
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                "ToDoItem: $item"
            )
        }
    }
}