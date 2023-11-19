package ua.dev.webnauts.cleanproject.screen.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ua.dev.webnauts.cleanproject.database.UserDatabaseManagement
import ua.dev.webnauts.cleanproject.database.UserProfile
import ua.dev.webnauts.sqldelightsetup.db.Database
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val dataBase : Database,
    private val userDatabaseManagement : UserDatabaseManagement
) : ViewModel(){

    private val _isLogin = mutableStateOf<Boolean?>(null)
    val isLogin: State<Boolean?> get() = _isLogin

    private val _saveCount = mutableStateOf<Int?>(null)
    val saveCount: State<Int?> get() = _saveCount

    fun setRoundCorners(i: Int) {
        _saveCount.value = i
    }

    /***
     * Функция для проверки авторизации пользователя
     * */
    fun checkUser() {
        userDatabaseManagement.getUser()?.let {
            _isLogin.value = true
        } ?: kotlin.run { _isLogin.value = false }
    }


    /**
     * Функция для регистрации пользователя
     * */
    fun registerUser(
        email: String = "weert@gmail.com",
        firsName: String = "serhii",
        lastName: String = "Krainik",
        onSuccess:()->Unit ){

        userDatabaseManagement.insertUser(
            UserProfile(
                user = ua.dev.webnauts.cleanproject.database.User(
                    id = 0,
                    email = email,
                    firstName = firsName,
                    lastName = lastName,
                    phone = null,
                    avatar = null,
                    lang = null
                ),
                accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiNzZmNTA1ZjNmYjQ2ODc0NmVkOWYwZTM3ZjZjZDYzYzc4YWFiYTE3OTQxN2E4YmNjNWEzMjFjNDc4YWU1ODJiMWUwNmU0NzgwMDgxMzU1NGUiLCJpYXQiOjE2OTY1Nzg3NjkuNzI3MTM0LCJuYmYiOjE2OTY1Nzg3NjkuNzI3MTM2LCJleHAiOjE2OTc4NzQ3NjkuNzEwOTM2LCJzdWIiOiIxIiwic2NvcGVzIjpbIioiXX0.tV8qiR68PNqx-mYnfEp7f4Ljwp_4F7snnSGqoUkkwi1k-EnTx6-IdZuDAnWwu4fscskHGSj6oMO2ZqgI2YrOPA9QaE_VwmMJ89iUYBtDdZ72P2IgpxMxTfKA1HokYQlqkl6xvqGnRdFDN5E6aLj5opw0q7ApzcG5bAWvG6f-9Hto6HSoMPIN5g978cTHl9dbOe9os1OS9xKZi10vYtq8p1v1jjMVwKh8-J8ljdphkX2dXuSs9gU0MObTOiOEq8HCFR6EvIQeTsJd9mhp6A5iGdT12GIJ24hJLSnPQT0zXdgi6UT0gINnZG4yJQa4QXUuc2GPh-VaUBAEkni2NURZMBoypio8A1_OPLBfF4PABeraxLosPDuOGN-uNevvieWp1qMS0e4FtJVHk4HlwdEzIXou8ZpCEAnTg64Uj1zMiK4vntqGQu4udy0WYCY83nIbI-9ZwPfLIp6PUFwNFqDsSU-2UgXaHMfRx7A2ePFK0paIUse3lWExhmJUPwAHmmKHE56yVnb6XHW8uBlVWyR7D-Cv9VD_QzJ83HfnY_IpAm3AMt1ZgkHB66MZh-e69mbDcgedTWZtdRpFrTRuAb84BxFGjVzkEvpbrd06EHvJHvY-M05auxApdw1qY7fccQiAQ3KDJ8bazbuV2DvA9_zaclLPnHikWXFH21u2r2HRM2o",
                refreshToken =  "def5020061a82428a222fa4bd871d3d2f7b47fb45fcaadeceddb89f4368abb44b0c5ee45b3ba0b0a2b1bd453600137411d8a1f0a5a5c4254679a1388b30383dc58957076ebdddc0626fd41ab4525d1332e39c307a6a535204b32688d4cfc06226f7c256d913dfe48297fcd60f9a9cbfae10137277067c978532098d16e4410d01f446eb154524ba9c024efeb842baa513e0ef0f7427270c68f16124555589012a7ed64be056e3894d184519684c8932327fcd0c9d5b14a4858c421d1a6758667288825051e9315a3960cb20cc211ade5a10921ccc28b243ea50977a3320490927cc2f9df3eb44e70c8b490e6429fe761f703f7769473b67aa03b6ebc2720af1aac608462daa5e5ed01d3e8c1582dbed0bb12631be358eeaf7d662fbabf3df35aac352ae369cab2443160ded73247ac3db2d6fba1383185b6c316e1727495b982e1678555a6a01caa2d7741018936349f64f41aa42580cba873d2f1dd0f89dc203448dc64",
                userId = email
            )
        )

        onSuccess()
    }

}