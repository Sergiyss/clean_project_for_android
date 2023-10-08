package ua.dev.webnauts.cleanproject.network.ktor

interface ServiceApi {
    suspend fun login(): NetworkResponse<String>

}