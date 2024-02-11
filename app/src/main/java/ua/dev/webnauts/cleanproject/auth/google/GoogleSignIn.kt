package ua.dev.webnauts.cleanproject.auth.google

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import org.json.JSONException
import timber.log.Timber
import ua.dev.webnauts.cleanproject.R
import ua.dev.webnauts.cleanproject.utils.Coroutines
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class GoogleSignIn @Inject constructor(
    private val context: Context
) : IGoogleSignIn {
    override val googleSignInOptions =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

    override fun isUserGoogleSignedIn(context: Context): Boolean {
        return getUserGoogleInfo(context = context) != null
    }

    override fun isResult(
        task: Task<GoogleSignInAccount>?,
        errors: (String) -> Unit,
        onSuccess: (token: String, googleSignInAccount: GoogleSignInAccount) -> Unit,
    ): Boolean {
        println("isResult isResult isResult")
        if (task == null) {
            errors("Failed to get data")
            return false
        }
        val result: GoogleSignInAccount = task.getResult(ApiException::class.java)
        println("isResult isResult isResult ${result.serverAuthCode} ${result.email}")
        if (result.serverAuthCode != null) {
            Coroutines.io {
                getAcceptClient(result.serverAuthCode!!) {
                    onSuccess(it, result)
                }
            }
        }

        return true
    }

    override fun getUserGoogleInfo(context: Context): GoogleSignInAccount? {
        return GoogleSignIn.getLastSignedInAccount(context)
    }

    override fun getGoogleClient(context: Context): GoogleSignInClient {
        return GoogleSignIn.getClient(context, googleSignInOptions)
    }

    override fun logoutGoogle(context: Context) {
        getGoogleClient(context = context).signOut()
            .addOnSuccessListener {
                Timber.d("Logout success")
            }
            .addOnFailureListener {
                Timber.d("Logout error%s", it.message)
            }
    }

    override fun getAcceptClient(code: String, response: (String) -> Unit) {
        val client = OkHttpClient()

        val requestBody: RequestBody = FormBody.Builder()
            .add("grant_type", "authorization_code")
            .add(
                "client_id",
                "464794290410-f8cgucb2a4cla2v38eos847of50m5kgb.apps.googleusercontent.com"
            )
            .add("client_secret", "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")
            .add("redirect_uri", "")
            .add("code", code)
            .build()

        val request = Request.Builder()
            .url("https://www.googleapis.com/oauth2/v4/token")
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("errorrrr $e")
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    println("SUSSES AUTH $response")

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
        })
    }
}