package ua.dev.webnauts.cleanproject.network.ktor

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.HttpClient
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Описываю общее поведение для обращения к серверу
 *
 * */
@Singleton
class ServiceApiImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val client: HttpClient
) : ServiceApi, SafeApiCall() {


//    override suspend fun photoss(): NetworkResponse<List<PhotosResponseItem>> {
//        return safeCall(context) {
//            client.get(HttpRoutes.photoss) {
//                contentType(ContentType.Application.Json)
//            }
//        }
//    }
}