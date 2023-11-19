package ua.dev.webnauts.cleanproject

import android.app.Application
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import ua.dev.webnauts.sqldelightsetup.db.Database

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}