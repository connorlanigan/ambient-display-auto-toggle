package com.connorlanigan.ambientdisplayautotoggle

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_POWER_DISCONNECTED
import android.content.IntentFilter

private class PowerBroadcastReceiver : android.content.BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null || intent == null) {
            return
        }
        if (intent.action == Intent.ACTION_POWER_CONNECTED) {
            handleUpdate(context, true)
        } else if (intent.action == ACTION_POWER_DISCONNECTED) {
            handleUpdate(context, false)
        }

    }
}

private var receiverRegistered = false;

/**
 * This function registers a listener that is notified when a charger
 * is plugged into the device or unplugged.
 * It also starts a foreground service, so that the power events will
 * still be received when the app is closed.
 */
fun registerPowerEventBroadcastReceiver(applicationContext: Context) {
    if (receiverRegistered) {
        return
    }

    createAllNotificationChannels(applicationContext)

    val receiver = PowerBroadcastReceiver()
    val intentFilter = IntentFilter()
    intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED")
    intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED")
    applicationContext.registerReceiver(receiver, intentFilter)

    val intent = Intent(applicationContext, ForegroundService::class.java)
    applicationContext.startForegroundService(intent)

    receiverRegistered = true
}