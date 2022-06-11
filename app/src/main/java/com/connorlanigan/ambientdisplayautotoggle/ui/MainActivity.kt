package com.connorlanigan.ambientdisplayautotoggle.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.connorlanigan.ambientdisplayautotoggle.*
import com.connorlanigan.ambientdisplayautotoggle.ui.components.Toggle
import kotlinx.coroutines.launch
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        registerPowerEventBroadcastReceiver(applicationContext)

        setContent {
            AmbientDisplayAutoToggleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Main()
                }
            }
        }
    }
}

@Composable
fun Main() {
    val context = LocalContext.current
    val hasPermission = hasPermissionToUpdateAmbientDisplaySetting(context)
    val enabled by remember { getEnabled(context) }.collectAsState(initial = true)
    val showNotifications by remember { getShowNotifications(context) }.collectAsState(initial = true)
    val scope = rememberCoroutineScope()

    Column {
        Box(Modifier.padding(horizontal = 16.dp, vertical = 38.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("Ambient Display Auto Toggle", fontSize = 36.sp)
                Text("connorlanigan.com")

                OutlinedButton({
                    val licenseScreenIntent = Intent(context, OssLicensesMenuActivity::class.java)
                    startActivity(context, licenseScreenIntent, null)
                }) {
                    Text("Show third-party licenses")
                }
            }
        }

        if (hasPermission) {

            Toggle(
                "Disable Ambient Display while charging",
                description = "Ambient Display is disabled when the phone is plugged into a charger, and enabled when the phone is unplugged.",
                checked = enabled,
                onCheckedChange = { scope.launch { setEnabled(context, it) } }
            )

            Toggle(
                "Show a notification whenever Ambient Display is toggled",
                description = null,
                checked = showNotifications,
                onCheckedChange = { scope.launch { setShowNotifications(context, it) } },
                enabled = enabled
            )

        } else {
            Column(
                Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text("To use this app, you need to grant it permission to modify secure system settings.")
                Text("Connect this device to a computer, and run the following command on the computer:")
                Text(
                    "adb shell pm grant ${context.packageName} android.permission.WRITE_SECURE_SETTINGS",
                    fontFamily = FontFamily.Monospace
                )
                Text("After that command ran successfully, restart this app.")
            }
        }
    }
}
