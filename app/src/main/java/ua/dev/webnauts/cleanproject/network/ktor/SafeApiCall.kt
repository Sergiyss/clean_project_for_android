package ua.dev.webnauts.cleanproject.network.ktor

import android.content.Context
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.serialization.json.Json
import ua.dev.webnauts.cleanproject.cache.CacheManagerApi
import java.io.IOException
import kotlinx.serialization.encodeToString


abstract class SafeApiCall {

    suspend inline fun <reified T : Any> safeCall(
        context: Context,
        cacheKey: String? = null,
        request: () -> HttpResponse,
    ): NetworkResponse<T> {

        return try {
            request().let { response ->
                if (response.status.isSuccess()) {

                    if(cacheKey != null) {
                        val cache = CacheManagerApi(context)
                        cache.saveData(cacheKey, Json.encodeToString(response.body<T>()))
                    }
                    NetworkResponse.Success(response.body())
                } else {

                    NetworkResponse.Error(
                        response.body(),
                        message = response.status.description,
                        code = response.status.value
                    )
                }
            }
        } catch (e: NoTransformationFoundException) {
            NetworkResponse.Error(
                message = e.message ?: e.stackTraceToString(),
                code = -2
            )
        } catch (e: IOException) {
            if(cacheKey != null) {
                val cache = CacheManagerApi(context)
                val json = cache.getData(cacheKey)
                if(json != null) {
                    val response = Json.decodeFromString<T>(json)

                   return NetworkResponse.Cache(response )
                }
            }
            NetworkResponse.Error(
                message = e.message ?: e.stackTraceToString(),
                code = -1
            )
        } catch (e: Exception) {
            NetworkResponse.Error(
                message = e.message ?: e.stackTraceToString(),
                code = -3
            )
        }

    }
}