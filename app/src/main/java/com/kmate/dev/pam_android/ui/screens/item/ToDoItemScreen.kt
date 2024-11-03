package com.kmate.dev.pam_android.ui.screens.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ToDoItemScreen(
    viewModel: ToDoItemViewModel,
    itemId: Int
) {
    ToDoItemScreen(
        itemId = itemId
    )
}

@Composable
fun ToDoItemScreen(
    itemId: Int
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "This screen should show details of item: $itemId"
        )
    }
}