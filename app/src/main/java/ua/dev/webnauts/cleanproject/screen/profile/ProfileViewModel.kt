package ua.dev.webnauts.cleanproject.screen.profile

import android.view.View
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.dev.webnauts.cleanproject.database.UserDatabaseManagement
import ua.dev.webnauts.cleanproject.database.UserProfile
import java.lang.reflect.Array.get
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    val userDatabaseManagement: UserDatabaseManagement
) : ViewModel() {

   private val _getUser = mutableStateOf<UserProfile?>(null)
        val getUser: State<UserProfile?> get() = _getUser

    fun getUserData() {
        CoroutineScope(Dispatchers.IO).launch {
            _getUser.value = userDatabaseManagement.getUser()
        }
    }

    fun exitUser(userId : String, success: () -> Unit){
        userDatabaseManagement.deleteUser(userId)
        success()
    }
}