package ua.dev.webnauts.cleanproject.database.datastore

import androidx.datastore.core.DataStore
import ua.dev.webnauts.cleanproject.database.datastore.data.TokenData
import ua.dev.webnauts.cleanproject.database.datastore.data.UserData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import timber.log.Timber
import ua.dev.webnauts.cleanproject.SettingsProto
import ua.dev.webnauts.cleanproject.utils.ukToUaOrDefault
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProtoRepository @Inject constructor(
    private val dataStore: DataStore<SettingsProto>,
) : ProtoApi {

    override val tokenData: Flow<TokenData>
        get() = dataStore.data.map { settings ->
            try {
                settings.tokenData.toTokenData()
            } catch (e: Exception) {
                Timber.d(e.message)
                throw e
            }
        }.distinctUntilChanged()


    override suspend fun setTokenData(tokenData: TokenData) {
        dataStore.updateData { settings ->
            settings.toBuilder().setTokenData(tokenData.toSettingsTokenData()).build()
        }
    }

    override suspend fun clearTokenData() {
        dataStore.updateData { settings ->
            settings.toBuilder().clearTokenData().build()
        }
    }

    override val locale: Flow<String>
        get() = dataStore.data.map { settings ->
            try {
                settings.locale
            } catch (e: Exception) {
                Timber.d(e.message)
                throw e
            }
        }.distinctUntilChanged()

    override suspend fun setLocale(locale: String) {
        dataStore.updateData { settings ->
            settings.toBuilder().setLocale(locale).build()
        }
    }

    override suspend fun clearLocale() {
        val defaultLanguage = Locale.getDefault().language.ukToUaOrDefault()
        dataStore.updateData { settings ->
            settings.toBuilder().setLocale(defaultLanguage).build()
        }
    }

    override val userData: Flow<UserData>
        get() = dataStore.data.map { settings ->
            try {
                settings.userData.toUserData()
            } catch (e: Exception) {
                Timber.d(e.message)
                throw e
            }
        }.distinctUntilChanged()

    override suspend fun setUserData(userData: UserData) {
        dataStore.updateData { settings ->
            settings.toBuilder().setUserData(userData.toSettingsUserData()).build()
        }
    }


    override suspend fun clearUserData() {
        dataStore.updateData { settings ->
            settings.toBuilder().clearUserData().build()
        }
    }

    override suspend fun clearAllData() {
        clearTokenData()
        clearLocale()
        clearUserData()
    }
}