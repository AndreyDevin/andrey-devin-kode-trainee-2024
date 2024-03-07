package com.example.kode2024_test

import android.app.Application
import com.example.kode2024_test.di.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(koinModule)
        }

    }
}
