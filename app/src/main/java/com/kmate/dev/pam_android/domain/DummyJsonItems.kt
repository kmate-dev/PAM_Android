package com.kmate.dev.pam_android.domain

import kotlinx.serialization.Serializable

@Serializable
data class DummyJsonItems(
    val todos: List<DummyJsonToDoItem>
)
