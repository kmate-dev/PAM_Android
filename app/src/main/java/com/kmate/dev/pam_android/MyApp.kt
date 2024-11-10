package com.kmate.dev.pam_android

import android.app.Application
import com.kmate.dev.pam_android.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(appModule)
            // Not necessary - makes koin log som info to console
            androidLogger()
        }
    }
}
