package ua.dev.webnauts.cleanproject.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.*
import io.ktor.utils.io.core.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import kotlin.math.roundToInt


@HiltWorker
class ChunkedDownloadListenableWorker @AssistedInject constructor(
    @Assisted  val context: Context,
    @Assisted  val params: WorkerParameters,
    private val client: HttpClient
) : CoroutineWorker(context, params) {

    init {
        createChannel(context)
    }
    //https://stackoverflow.com/questions/76304094/kotlin-could-not-instantiate-couroutineworker-java-lang-nosuchmethodexception
    //https://stackoverflow.com/questions/71618805/hilt-and-workmanager-error-java-lang-nosuchmethodexceptioninit-class-andro
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        val url = params.inputData.downloadUrl()
        val outputFile = File(params.inputData.outputFilename())
        if (outputFile.exists()) outputFile.delete()

        client.prepareGet(urlString = url).execute { response ->
            val length = response.contentLength()?.toFloat() ?: 0F
            var readBytes = 0
            var progress = 0
            val channel: ByteReadChannel = response.body()
            while (!channel.isClosedForRead) {
                val packet = channel.readRemaining(limit = DEFAULT_BUFFER_SIZE.toLong())
                while (packet.isNotEmpty) {
                    val bytes: ByteArray = packet.readBytes()
                    outputFile.appendBytes(array = bytes)
                    readBytes += bytes.size
                    val currentProgress = (readBytes * 100F / length).roundToInt()
                    if (currentProgress != progress) {
                        progress = currentProgress
                        val progressData = progressData(
                            urlString = url,
                            outputFilename = outputFile.name,
                            progress = progress
                        )
                        setProgress(progressData)
                    }
                }
            }
        }

        Result.success(
            workDataOf(
                RESULT_WORK_KEY to "Hello ${(0 .. 1000).random()}"
            )
        )
    }

    override suspend fun getForegroundInfo(): ForegroundInfo = foregroundInfo(context, params.inputData.downloadUrl())
}