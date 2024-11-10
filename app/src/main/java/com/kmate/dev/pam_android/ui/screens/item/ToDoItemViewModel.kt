package com.kmate.dev.pam_android.ui.screens.item

import androidx.lifecycle.ViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ToDoItemViewModel: ViewModel(), KoinComponent {
    val injectedStringExample: String by inject()
}