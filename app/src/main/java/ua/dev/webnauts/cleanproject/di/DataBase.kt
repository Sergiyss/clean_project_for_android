package ua.dev.webnauts.cleanproject.di

import android.app.Application
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ua.dev.webnauts.sqldelightsetup.db.Database
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataBase {

    @Provides
    @Singleton
    fun provideAndroidPartyDatabaseFactoryDriver(
        application: Application
    ): Database {
        val database = AndroidSqliteDriver(Database.Schema, application, "Database.db")
        return Database(database)
    }

}