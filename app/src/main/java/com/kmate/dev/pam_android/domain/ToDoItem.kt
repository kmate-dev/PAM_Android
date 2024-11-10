package com.kmate.dev.pam_android.domain

import kotlinx.serialization.Serializable

@Serializable
data class ToDoItem(
    val id: Int,
    val name: String,
    val completed: Boolean
)