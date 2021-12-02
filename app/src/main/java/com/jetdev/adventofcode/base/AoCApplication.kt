package com.jetdev.adventofcode.base

import android.app.Application
import com.jetdev.adventofcode.BuildConfig
import com.jetdev.adventofcode.di.appModule
import com.jetdev.adventofcode.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class AoCApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@AoCApplication)
            modules(appModule, remoteModule)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}