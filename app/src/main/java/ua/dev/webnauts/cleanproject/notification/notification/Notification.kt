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
import ua.rk.Application
import ua.rk.MainActivity
import ua.rk.R

private var notificationId = 101

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

    /*val intent = Intent(context, SplashActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }*/
    val pendingIntent: PendingIntent =
        PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    /*val pendingIntent: PendingIntent =
        NavDeepLinkBuilder(context)
            .setComponentName(MainActivity::class.java)
            .setGraph(navGraph)
            .setDestination(destination)
            .createPendingIntent()*/

    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.mipmap.logo_app)
        //.setColor(R.color.md_cyan_300)
        .setContentTitle(title)
        .setContentText(message)
        .setPriority(NotificationCompat.PRIORITY_MAX)
        //.setLargeIcon(largeIcon) //если нужна будет большая иконка BitmapFactory.decodeResource(resources, R.drawable.logo)
        .setContentIntent(pendingIntent)
        .setStyle(style)
        .setAutoCancel(true)
        .setCategory(NotificationCompat.CATEGORY_ALARM)
        .setDefaults(NotificationCompat.DEFAULT_ALL)
        .setSilent(false)
        //.setVibrate(longArrayOf(500, 1500, 500, 1000))
    val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
    if (!pm.isInteractive) context.startActivity(intent)
    with(NotificationManagerCompat.from(context)) {
        notify(tag, idNotification, builder.build())
    }

    //playSound(context)
}

fun removeNotification(context: Context, tag: String = "", id: Int) {
    NotificationManagerCompat.from(context).cancel(tag, id)
}

fun clearNotifications(context: Context) {
    NotificationManagerCompat.from(context).cancelAll()
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

private fun playSound(context: Context) {
    try {
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val ringtone = RingtoneManager.getRingtone(context, soundUri)
        ringtone.play()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}