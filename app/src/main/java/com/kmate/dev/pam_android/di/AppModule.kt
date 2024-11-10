package com.kmate.dev.pam_android.di

import android.content.Context
import com.kmate.dev.pam_android.data.local.LocalDataStore
import com.kmate.dev.pam_android.data.local.LocalDataStoreImpl
import com.kmate.dev.pam_android.ui.screens.item.ToDoItemViewModel
import com.kmate.dev.pam_android.ui.screens.list.ToDoListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<LocalDataStore>{
        LocalDataStoreImpl(
            sharedPreferences = androidContext().getSharedPreferences(
                "app_preferences",
                Context.MODE_PRIVATE
            )
        )
    }

    viewModel {
        ToDoListViewModel()
    }
    viewModel {
        ToDoItemViewModel(get())
    }
}