package ua.dev.webnauts.cleanproject.auth.google

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import timber.log.Timber
import ua.dev.castleguide.di.GoogleApiContract


@Composable
fun authGoogleLauncher(
    googleSignInOptions: GoogleSignInOptions,
    onTask: (task: Task<GoogleSignInAccount>?) -> Boolean,
): ManagedActivityResultLauncher<Int, Task<GoogleSignInAccount>?> {
    return rememberLauncherForActivityResult(contract = GoogleApiContract(googleSignInOptions)) { task ->
        try {
            val isResult = onTask(task)
            Timber.d("Google auth result is %s", isResult)
        } catch (e: ApiException) {
            Timber.d("Error %s", e.toString())
        }
    }
}