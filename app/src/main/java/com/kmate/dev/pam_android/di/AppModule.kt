package com.kmate.dev.pam_android.di

import com.kmate.dev.pam_android.ui.screens.item.ToDoItemViewModel
import com.kmate.dev.pam_android.ui.screens.list.ToDoListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<String>{
        "Hello Dependency Injection with Koin!"
    }

    viewModel {
        ToDoListViewModel()
    }
    viewModel {
        ToDoItemViewModel(get())
    }
}