package ua.dev.webnauts.cleanproject.database

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement
import ua.dev.webnauts.sqldelightsetup.db.Database
import javax.inject.Inject

@Serializable
data class User(
    @SerialName("id")
    val id: Int,
    @SerialName("email")
    val email: String,
    @SerialName("firstName")
    val firstName: String,
    @SerialName("lastName")
    val lastName: String,
    @SerialName("fullName")
    val fullName : String = "$firstName $lastName",
    @SerialName("phone")
    val phone : String?,
    @SerialName("avatar")
    val avatar : String?,
    @SerialName("lang")
    val lang : String?,
)
@Serializable
data class UserProfile(
    @SerialName("userId")
    val userId: String? = null,
    @SerialName("user")
    val user : User,
    @SerialName("accessToken")
    val accessToken : String,
    @SerialName("refreshToken")
    val refreshToken : String,
)

class UserDatabaseManagement @Inject constructor(
    private val database: Database){

   companion object{
       var userProfile : UserProfile? = null
   }

    /***
     * Функция записи и обновления пользователя
     * @param _userProfile -> дата с пользователем
     *
     * После чего данные можно получить глобально через userProfile
     * */
    fun insertUser(_userProfile: UserProfile){

        userProfile = _userProfile

        val data : String =  Json.encodeToString(_userProfile)

        database.databaseQueries.insertUser(0, _userProfile.user.email, data)
    }


    fun getUser() : UserProfile?{
        database.databaseQueries.allUsers().executeAsList().firstOrNull()?.usersData?.let {
            userProfile = Json.decodeFromString<UserProfile>(it)

            return userProfile
        } ?: kotlin.run { return null }
    }

    fun deleteUser(userId : String){
        database.databaseQueries.deleteUser(userId)
    }

}