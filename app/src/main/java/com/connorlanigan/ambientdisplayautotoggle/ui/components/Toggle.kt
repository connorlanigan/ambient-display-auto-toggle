package com.connorlanigan.ambientdisplayautotoggle.ui.components

import androidx.compose.material.Switch
import androidx.compose.runtime.Composable

@Composable
fun Toggle(
    label: String,
    description: String?,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit),
    enabled: Boolean = true,
) {

    Setting(
        label, description, if (enabled) { ->
            onCheckedChange(!checked)
        } else null
    ) {
        Switch(
            checked = checked,
            onCheckedChange = null,
            enabled = enabled
        )
    }
}
