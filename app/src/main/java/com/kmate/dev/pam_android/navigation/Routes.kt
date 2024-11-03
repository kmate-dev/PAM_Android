package com.kmate.dev.pam_android.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object ToDoListRoute: Route
    @Serializable
    data class ToDoItemRoute(
        val itemId: Int
    ): Route
}
