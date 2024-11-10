package com.kmate.dev.pam_android.ui.screens.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel

@Composable
fun ToDoItemScreen(
    viewModel: ToDoItemViewModel = koinViewModel(),
    itemId: Int
) {
    ToDoItemScreen(
        itemId = itemId,
        injectedString = viewModel.injectedStringExample
    )
}

@Composable
fun ToDoItemScreen(
    itemId: Int,
    injectedString: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(
                injectedString
            )
            Text(
                "This screen should show details of item: $itemId"
            )
        }
    }
}