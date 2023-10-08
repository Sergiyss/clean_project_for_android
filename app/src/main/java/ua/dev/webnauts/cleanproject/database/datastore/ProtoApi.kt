package ua.dev.webnauts.cleanproject.database.datastore

import ua.dev.webnauts.cleanproject.database.datastore.data.TokenData
import ua.dev.webnauts.cleanproject.database.datastore.data.UserData
import kotlinx.coroutines.flow.Flow

interface ProtoApi {

    val tokenData: Flow<TokenData>

    suspend fun setTokenData(tokenData: TokenData)

    suspend fun clearTokenData()

    val locale: Flow<String>

    suspend fun setLocale(locale: String)

    suspend fun clearLocale()

    val userData: Flow<UserData>

    suspend fun setUserData(userData: UserData)

    suspend fun clearUserData()

    /**
     * Datastore cleanup method
     * */
    suspend fun clearAllData()

}