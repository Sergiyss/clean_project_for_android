package ua.dev.webnauts.cleanproject.database.room

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

class RoomDbInitializer(
    private val userProvider: Provider<UserDao>,
) : RoomDatabase.Callback() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            //Вызываю метод
        }
    }
}
