package com.connorlanigan.ambientdisplayautotoggle

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService

fun createAllNotificationChannels(context: Context) {
    createNotificationChannel(
        context,
        "permissions_missing",
        "Permissions missing",
    )
    createNotificationChannel(
        context,
        "toggled",
        "Ambient Display was toggled automatically",
    )
    createNotificationChannel(
        context,
        "service_running",
        "App is running in the background"
    )
}

private fun createNotificationChannel(context: Context, id: String, name: String) {
    val channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_MIN)
    val notificationManager =
        getSystemService(context, NotificationManager::class.java) as NotificationManager
    notificationManager.createNotificationChannel(channel)
}

fun showNotification(
    context: Context,
    channel: String,
    notificationId: Int,
    title: String,
    description: String
) {
    val notification = NotificationCompat.Builder(context, channel)
        .setSmallIcon(R.drawable.ic_stat_adat)
        .setContentTitle(title)
        .setContentText(description)
        .build()

    with(NotificationManagerCompat.from(context)) {
        notify(notificationId, notification)
    }
}
