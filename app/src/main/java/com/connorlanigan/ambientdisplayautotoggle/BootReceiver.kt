package com.connorlanigan.ambientdisplayautotoggle

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * This broadcast receiver ensures that the app is
 * launched when the device reboots.
 */
class BootReceiver : BroadcastReceiver() {
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent?) {
        registerPowerEventBroadcastReceiver(context.applicationContext)
    }
}