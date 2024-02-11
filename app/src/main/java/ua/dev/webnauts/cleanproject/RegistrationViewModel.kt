package ua.dev.webnauts.cleanproject

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ua.dev.webnauts.cleanproject.auth.google.IGoogleSignIn
import ua.dev.webnauts.cleanproject.network.ktor.ServiceApi
import javax.inject.Inject


@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val serviceApi: ServiceApi,
    private val savedStateHandle: SavedStateHandle,
    private val iGoogleSignIn: IGoogleSignIn,
) : ViewModel() {

    val googleSignInOptions = iGoogleSignIn.googleSignInOptions

    fun logoutGoogle(context: Context) {
        iGoogleSignIn.logoutGoogle(context)
    }

    /**
     * Вход спомощью гугл
     * **/
    fun isResultGoogle(task: Task<GoogleSignInAccount>?,
                       error: (String) -> Unit = {},
                       isRegisterGoogle: (Boolean, String, GoogleSignInAccount) -> Unit,
                       isLoginGoogle: () -> Unit): Boolean {

        println(">>>>>>> LOGIN 2 ${task?.result?.email}")

        return iGoogleSignIn.isResult(task,
            error = error
        ) { _, googleSignInAccount  ->

            viewModelScope.launch {

            }
        }
    }
}