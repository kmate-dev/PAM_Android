package com.kmate.dev.pam_android.domain

import kotlinx.serialization.Serializable

@Serializable
data class DummyJsonToDoItem(
    val id: Int,
    val todo: String,
    val completed: Boolean,
    val userId: Int
)
