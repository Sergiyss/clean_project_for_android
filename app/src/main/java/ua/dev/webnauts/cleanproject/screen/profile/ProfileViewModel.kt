package ua.dev.webnauts.cleanproject.screen.profile

import android.view.View
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ua.dev.webnauts.cleanproject.database.UserDatabaseManagement
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val userDatabaseManagement: UserDatabaseManagement
) : ViewModel() {

    fun exitUser(userId : String, success: () -> Unit){
        userDatabaseManagement.deleteUser(userId)
        success()
    }
}