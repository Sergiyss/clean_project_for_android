package ua.dev.webnauts.cleanproject.auth.google

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task

interface IGoogleSignIn {

    val googleSignInOptions: GoogleSignInOptions

    fun isUserGoogleSignedIn(context: Context): Boolean

    fun isResult(
        task: Task<GoogleSignInAccount>?,
        error: (String) -> Unit = {},
        onSuccess: (token: String, googleSignInAccount: GoogleSignInAccount) -> Unit,
    ): Boolean

    fun getUserGoogleInfo(context: Context): GoogleSignInAccount?

    fun getGoogleClient(context: Context): GoogleSignInClient

    fun logoutGoogle(context: Context)

    fun getAcceptClient(code: String, response:(String)-> Unit)

}