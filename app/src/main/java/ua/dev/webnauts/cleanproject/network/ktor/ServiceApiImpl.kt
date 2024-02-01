package ua.dev.webnauts.cleanproject.network.ktor

import android.content.Context
import android.os.Environment
import android.system.Os
import android.system.Os.close
import com.conena.nanokt.android.util.logDebug
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.util.cio.writeChannel
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import okio.*


import java.io.File
import java.nio.ByteBuffer
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

    override suspend fun downloadFileLink(link: String): Flow<NetworkResponse<String>> {
        return callbackFlow {
            trySend(NetworkResponse.Loading())

            try {
                val httpResponse: HttpResponse = client.get(link)
                //val channel: ByteReadChannel = httpResponse.body()


                val downloadsDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                val file = withContext(Dispatchers.IO) {
                    File(downloadsDirectory, "image_${System.currentTimeMillis()}.jpg")
                }

                val responseBody: ResponseBody? = httpResponse.body()

                try {
                    file.sink().buffer().use { sink ->
                        responseBody!!.source().use { source ->
                            sink.writeAll(source)
                        }
                    }
                    logDebug("Файл был загружен....")
                    trySend(NetworkResponse.Success(file.absolutePath))
                } catch (e: Exception) {
                    trySend(
                        NetworkResponse.Error(
                            message = "Error downloading file: ${e.message}",
                            code = -4
                        )
                    )
                } finally {
                    close()
                }
            } catch (e: Exception) {
                trySend(
                    NetworkResponse.Error(
                        message = "Error downloading file: ${e.message}",
                        code = -4
                    )
                )
            } finally {
                close()
                awaitClose {
                    close()
                }
            }
        }
    }


}