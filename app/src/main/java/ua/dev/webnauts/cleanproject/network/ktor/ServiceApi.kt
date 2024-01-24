package ua.dev.webnauts.cleanproject.network.ktor

import kotlinx.coroutines.flow.Flow

interface ServiceApi {
   // suspend fun login(): NetworkResponse<String>
    suspend fun downloadFileLink(link : String) : Flow<NetworkResponse<String>>
}