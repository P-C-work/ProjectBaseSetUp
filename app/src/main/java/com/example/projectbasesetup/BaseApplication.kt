package com.example.projectbasesetup

import android.app.Application
import com.example.projectbasesetup.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.logger.Level

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BaseApplication)
            androidLogger(Level.ERROR)
            modules(allModules)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}