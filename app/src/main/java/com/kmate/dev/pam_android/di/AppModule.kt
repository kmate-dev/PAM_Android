package com.kmate.dev.pam_android.di

import org.koin.dsl.module

val appModule = module {
    single<String>{
        "Hello Dependency Injection with Koin!"
    }
}