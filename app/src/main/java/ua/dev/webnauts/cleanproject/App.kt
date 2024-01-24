package ua.dev.webnauts.cleanproject

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import ua.dev.webnauts.sqldelightsetup.db.Database
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {


    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    lateinit var workManager : WorkManager

    companion object {
        private var isMainActivityStarted: Boolean = false

        fun isMainActivityStarted(): Boolean {
            return isMainActivityStarted
        }

        var fcmToken: String? = null

    }
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        workManager = WorkManager.getInstance(this)
    }

    override val workManagerConfiguration: Configuration
        get() =Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
}