package ua.dev.webnauts.cleanproject.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ua.dev.webnauts.cleanproject.R
import ua.dev.webnauts.cleanproject.database.room.RoomDbInitializer
import ua.dev.webnauts.cleanproject.database.room.User
import ua.dev.webnauts.cleanproject.database.room.UserDao
import javax.inject.Provider
import javax.inject.Singleton

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        userProvider: Provider<UserDao>,
    ): AppDatabase {

        val appNameBD = context.getString(R.string.app_name).lowercase().replace(" ", "_")

        println("???? >>>> ${appNameBD} ")

        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "$appNameBD.db"
        ).addCallback(
            /**
             * Attach [RoomDbInitializer] as callback to the database
             * Этот addCallbackметод используется для присоединения экземпляра RoomDbInitializerк
             * базе данных. Это гарантирует, что при первом создании базы данных будет вызываться
             * onCreateметод RoomDbInitializerдля заполнения базы данных статическими данными.
             */
            RoomDbInitializer(userProvider = userProvider)
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao

}