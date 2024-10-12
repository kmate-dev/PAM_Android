package com.kmate.dev.pam_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kmate.dev.pam_android.domain.ToDoItem
import com.kmate.dev.pam_android.ui.screens.ToDoViewModel
import com.kmate.dev.pam_android.ui.theme.PAM_AndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val toDoViewModel = ToDoViewModel()
        setContent {
            PAM_AndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ToDoScreen(
                        isLoading = toDoViewModel.isLoading,
                        todosList = toDoViewModel.todosList,
                        onAddToDoItem = toDoViewModel::addToDoItem,
                        onCompletedClicked = toDoViewModel::completeItem
                    )
                }
            }
        }
    }
}

@Composable
fun ToDoScreen(
    isLoading: Boolean,
    todosList: List<ToDoItem>,
    onAddToDoItem: (String) -> Unit,
    onCompletedClicked: (Int) -> Unit
) {
    var newItemValue: String by remember {
        mutableStateOf("")
    }
    if(isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        Column {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = newItemValue,
                onValueChange = {
                    newItemValue = it
                }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    onAddToDoItem(newItemValue)
                }) {
                Text(
                    text = "Add note"
                )
            }
            LazyColumn(
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                items(todosList) {
                    ToDoItem(
                        item = it,
                        onCompletedClicked = onCompletedClicked
                    )
                }
            }
        }
    }
}

@Composable
fun ToDoItem(
    item: ToDoItem,
    onCompletedClicked: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = item.name
        )
        RadioButton(
            selected = item.completed,
            onClick = {
                onCompletedClicked(item.id)
            }
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun ToDoScreenPreview() {
    PAM_AndroidTheme {
        ToDoScreen(
            false,
            listOf(
                ToDoItem(0,
                    "Prepare Android Example",
                    false
                ),
                ToDoItem(
                    1,
                    "Prepare iOS Example",
                    false
                )
            ),
            {},
            {}
        )
    }
}