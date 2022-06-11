package com.connorlanigan.ambientdisplayautotoggle

import android.content.Context
import android.provider.Settings
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

/**
 * This function updates the "Always On" setting in response
 * to a power change event.
 */
fun handleUpdate(context: Context, chargerIsConnected: Boolean) {
    val enabled = runBlocking { getEnabled(context).first() }
    if (!enabled) {
        return
    }
    if (!hasPermissionToUpdateAmbientDisplaySetting(context)) {
        showNotification(
            context,
            "permissions_missing",
            2,
            "Additional permissions are needed",
            "This app needs additional permissions in order to toggle Ambient Display automatically."
        )
        return
    }

    val showNotifications = runBlocking { getShowNotifications(context).first() }

    if (chargerIsConnected) {
        setAmbientDisplayOn(context, false)
        if (showNotifications) {
            showNotification(
                context,
                "toggled",
                1,
                "Ambient Display has been disabled",
                "The device was plugged into a charger."
            )
        }
    } else {
        setAmbientDisplayOn(context, true)
        if (showNotifications) {
            showNotification(
                context,
                "toggled",
                1,
                "Ambient Display has been enabled",
                "The device was unplugged from a charger."
            )
        }
    }

}

/**
 * This function toggles the "Always On" setting.
 */
fun setAmbientDisplayOn(context: Context, value: Boolean) {
    Settings.Secure.putInt(context.contentResolver, "doze_always_on", if (value) 1 else 0)
}

fun hasPermissionToUpdateAmbientDisplaySetting(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        "android.permission.WRITE_SECURE_SETTINGS"
    ) == PermissionChecker.PERMISSION_GRANTED
}