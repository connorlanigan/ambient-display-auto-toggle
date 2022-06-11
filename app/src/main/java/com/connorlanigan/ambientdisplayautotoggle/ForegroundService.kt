package com.connorlanigan.ambientdisplayautotoggle

import android.app.Notification
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE
import androidx.core.app.NotificationCompat.PRIORITY_MIN

/**
 * This service allows the app to keep running, even when the app
 * is "closed" from the activity switcher by the user.
 */
class ForegroundService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val notification: Notification = NotificationCompat.Builder(this, "service_running")
            .setSmallIcon(R.drawable.ic_stat_adat)
            .setContentTitle("App is waiting in the background")
            .setContentText("Disable this notification in the system's app settings.")
            .setPriority(PRIORITY_MIN)
            .setForegroundServiceBehavior(FOREGROUND_SERVICE_IMMEDIATE)
            .build()

        startForeground(25, notification)

        return START_STICKY
    }
}