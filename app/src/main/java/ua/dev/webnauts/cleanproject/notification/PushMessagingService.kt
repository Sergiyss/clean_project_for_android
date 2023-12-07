package ua.dev.webnauts.cleanproject.notification

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import ua.dev.webnauts.cleanproject.notification.notification.NotificationConstants
import ua.dev.webnauts.cleanproject.notification.notification.setNotification

private const val TAG = "FirebaseMsgService"

@AndroidEntryPoint
class PushMessagingService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.w("MainActivity", "FirebaseMessaging token: $token")
        sendRegistrationToServer(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {


        val title = message.notification?.title
        val text = message.notification?.body
        val dataTitle = message.data["title"].toString()
        val bodyTitle = message.data["body"].toString()

        setNotification(
            context = applicationContext,
            title = title?:dataTitle,
            message = text?:bodyTitle,
            tag = NotificationConstants.FCM,
            channelId = NotificationConstants.FCM,
            idNotification = message.from?.toIntOrNull()?:102
        )

    }

    private fun sendRegistrationToServer(token: String?) {
        // TODO: Implement this method to send token to your app server.
        Log.d(TAG, "sendRegistrationTokenToServer($token)")
    }

}