package com.kmate.dev.pam_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kmate.dev.pam_android.domain.ToDoItem
import com.kmate.dev.pam_android.ui.theme.PAM_AndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PAM_AndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ToDoScreen(
                        listOf(
                            ToDoItem(
                                "Prepare Android Example",
                                false
                            ),
                            ToDoItem(
                                "Prepare iOS Example",
                                false
                            )
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ToDoScreen(todosList: List<ToDoItem>) {
    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = "",
            onValueChange = {}
        )
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                //viewmodel....
            }) {
            Text(
                text = "Add note"
            )
        }
        LazyColumn(
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            items(todosList) {
                ToDoItem(item = it)
            }
        }
    }
}

@Composable
fun ToDoItem(item: ToDoItem) {
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

            }
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun GreetingPreview() {
    PAM_AndroidTheme {
        ToDoScreen(
            listOf(
                ToDoItem(
                    "Prepare Android Example",
                    false
                ),
                ToDoItem(
                    "Prepare iOS Example",
                    false
                )
            )
        )
    }
}