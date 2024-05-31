package com.anyarusova.smarthouse

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DeviceList(
    devices: List<SmartDevice>,
    onEditDevice: (SmartDevice) -> Unit,
    onToggleDeviceStatus: (SmartDevice) -> Unit
) {
    Column {
        devices.forEach { device ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { onEditDevice(device) },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(text = device.name, style = MaterialTheme.typography.headlineMedium)
                    Text(text = device.type, style = MaterialTheme.typography.bodyMedium)
                }
                Switch(
                    checked = device.status,
                    onCheckedChange = { onToggleDeviceStatus(device) }
                )
            }
            Divider(color = Color.Gray, thickness = 1.dp)
        }
    }
}
