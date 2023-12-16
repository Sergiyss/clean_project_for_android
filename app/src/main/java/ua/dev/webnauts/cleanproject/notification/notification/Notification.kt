package ua.dev.webnauts.cleanproject.notification.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.media.RingtoneManager
import android.os.Build
import android.os.PowerManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import ua.dev.webnauts.cleanproject.App
import ua.dev.webnauts.cleanproject.MainActivity
import ua.dev.webnauts.cleanproject.R


private var notificationId = 101


/**
 * Пуш уведомления которые поддерживают многострочность и вложеность сообщений в группу
 *
 * @author serhii kr
 * @version 1.1
 * **/
@SuppressLint("ResourceAsColor", "MissingPermission")
fun setNotification(
    context: Context,
    title: String,
    message: String,
    channelId: String,
    tag: String,
    largeIcon: Bitmap? = null,
    style: NotificationCompat.Style? = null, //для дополнительной настройки, например: большой текст,
    idNotification: Int,
    // картинка NotificationCompat.BigTextStyle()
    //                .bigText("Much longer text that cannot fit one line...")
    //          NotificationCompat.BigPictureStyle()
    //                .bigPicture(myBitmap)
    //                .bigLargeIcon(null)
    //navGraph: NavGraph,
    //destination: String,
) {
    createNotificationChannel(channelId, context)

    val intent =
        if (App.isMainActivityStarted()) Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        else Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }




    //.setVibrate(longArrayOf(500, 1500, 500, 1000))
    val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
    if (!pm.isInteractive) context.startActivity(intent)



    val SUMMARY_ID = 0
    val GROUP_KEY_WORK_EMAIL = "com.android.example.WORK_EMAIL"

    val bigTextStyle = NotificationCompat.BigTextStyle()
        .bigText( message)

    val newMessageNotification1 = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.mipmap.logo_app)
        .setContentTitle(title)
        .setContentText(message)
        .setStyle(bigTextStyle)
        .setGroup(GROUP_KEY_WORK_EMAIL)
        .build()



    val summaryNotification = NotificationCompat.Builder(context, channelId)
        .setContentTitle(title)
        // Set content text to support devices running API level < 24.
        .setContentText(message)
        .setSmallIcon(R.mipmap.logo_app)
        // Build summary info into InboxStyle template.
        .setStyle(NotificationCompat.InboxStyle())
        // Specify which group this notification belongs to.
        .setGroup(GROUP_KEY_WORK_EMAIL)
        // Set this notification as the summary for the group.
        .setGroupSummary(true)
        .build()



    // Генерация уникального идентификатора для каждого уведомления
    val emailNotificationId1_ = generateUniqueId()
    val emailNotificationId2_ = generateUniqueId()

    // Сохранение уникальных идентификаторов в постоянном хранилище
    saveNotificationId("emailNotificationId1", emailNotificationId1_, context)
    saveNotificationId("emailNotificationId2", emailNotificationId2_, context)


    // Восстановление уникальных идентификаторов из постоянного хранилища
    val emailNotificationId1 = restoreNotificationId("emailNotificationId1", context)
    val emailNotificationId2 = restoreNotificationId("emailNotificationId2",context)




    NotificationManagerCompat.from(context).apply {
        notify(emailNotificationId1, newMessageNotification1)

        notify(SUMMARY_ID, summaryNotification)
    }
}

private fun playSound(context: Context) {
    try {
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val ringtone = RingtoneManager.getRingtone(context, soundUri)
        ringtone.play()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

// Генерация уникального идентификатора
private fun generateUniqueId(): Int {
    return System.currentTimeMillis().toInt()
}

// Сохранение уникального идентификатора в SharedPreferences
private fun saveNotificationId(key: String, id: Int, context: Context) {
    val preferences = context.getSharedPreferences("notification_ids", Context.MODE_PRIVATE)
    preferences.edit().putInt(key, id).apply()
}

// Восстановление уникального идентификатора из SharedPreferences
private fun restoreNotificationId(key: String, context: Context): Int {
    val preferences = context.getSharedPreferences("notification_ids", Context.MODE_PRIVATE)
    return preferences.getInt(key, 0)
}



private fun createNotificationChannel(channelId: String, context: Context) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val mChannel = NotificationChannel(
            channelId,
            "General Notifications",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "This is default channel used for all other notifications"
            enableVibration(true)
            lockscreenVisibility = NotificationCompat.VISIBILITY_PUBLIC
            setShowBadge(true)
        }

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)
    }
}