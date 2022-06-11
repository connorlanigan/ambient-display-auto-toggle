package com.connorlanigan.ambientdisplayautotoggle.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * This component provides a setting item that can be
 * clicked to modify it.
 */
@Composable
fun Setting(
    label: String,
    description: String?,
    onClick: (() -> Unit)?,
    content: @Composable () -> Unit
) {
    val enabled = onClick != null

    val rowModifier = Modifier
        .then(if (enabled) Modifier.clickable { onClick?.invoke() } else Modifier)
        .padding(vertical = 16.dp, horizontal = 16.dp)


    val disabledColor = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)

    Row(
        rowModifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(Modifier.weight(1f)) {
            Text(label, color = if (enabled) Color.Unspecified else disabledColor)

            if (description != null) {
                Text(
                    description,
                    style = MaterialTheme.typography.body2,
                    color = if (enabled) MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium) else disabledColor
                )
            }
        }

        Box(Modifier.padding(start = 8.dp)) {
            content()
        }
    }
}
