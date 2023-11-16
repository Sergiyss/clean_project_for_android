package ua.dev.webnauts.cleanproject

import android.app.Application
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import ua.dev.webnauts.sqldelightsetup.db.Database

@HiltAndroidApp
class App : Application() {

    lateinit var database: AndroidSqliteDriver

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        database  = AndroidSqliteDriver(Database.Schema, applicationContext, "Database.db")



        val database = Database(database)
        database.databaseQueries.insertUser(1, "Pratik", "test2@emai.com")
        database.databaseQueries.insertUser(2, "Pratik4", "email@gmail.com")
        database.databaseQueries.insertUser(3, "Pratik5", "email@gmail.com")
        database.databaseQueries.insertUser(4, "Pratik6", "email@gmail.com")
        database.databaseQueries.insertUser(5, "Pratik7", "email@gmail.com")

        database.database_appQueries.insertDataBaseApp(1, "Pratik", "321")


//        //DatabaseQueries
//        println(">>>>>>>>>>> ${database.databaseQueries}")
//        println(database)
//
//        val user = database.databaseQueries.allUsers().executeAsList()
//        user.forEach {
//            println(">>>>>>>>>>> ${it.username} <<< ${it.email}")
//        }




    }

}