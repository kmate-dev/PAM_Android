package com.kmate.dev.pam_android.di

import android.content.Context
import com.kmate.dev.pam_android.data.local.LocalDataStore
import com.kmate.dev.pam_android.data.local.LocalDataStoreImpl
import com.kmate.dev.pam_android.data.remote.ApiService
import com.kmate.dev.pam_android.data.remote.ApiServiceImpl
import com.kmate.dev.pam_android.ui.screens.item.ToDoItemViewModel
import com.kmate.dev.pam_android.ui.screens.list.ToDoListViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
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

    single<ApiService> {
        ApiServiceImpl(
            client = HttpClient(OkHttp) {
                install(Logging)
                install(ContentNegotiation) {
                    json(
                        Json {
                            isLenient = true
                            ignoreUnknownKeys = true
                        }
                    )
                }
            }
        )
    }

    viewModel {
        ToDoListViewModel(get(), get())
    }
    viewModel {
        ToDoItemViewModel(get(), get(), get())
    }
}