package com.kmate.dev.pam_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.kmate.dev.pam_android.navigation.Route
import com.kmate.dev.pam_android.ui.screens.item.ToDoItemScreen
import com.kmate.dev.pam_android.ui.screens.list.ToDoListScreen
import com.kmate.dev.pam_android.ui.theme.PAM_AndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PAM_AndroidTheme {
                // A surface container using the 'background' color from the theme
                Scaffold (
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    AppNavHost(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AppNavHost(modifier: Modifier, navController: NavHostController = rememberNavController()) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.ToDoListRoute,
    ) {
        composable<Route.ToDoListRoute> {
            ToDoListScreen(
                onItemClicked = { itemId ->
                    navController.navigate(
                        Route.ToDoItemRoute(
                            itemId = itemId
                        )
                    )
                }
            )
        }
        composable<Route.ToDoItemRoute> {
            val args = it.toRoute<Route.ToDoItemRoute>()
            ToDoItemScreen(
                itemId = args.itemId
            )
        }
    }
}