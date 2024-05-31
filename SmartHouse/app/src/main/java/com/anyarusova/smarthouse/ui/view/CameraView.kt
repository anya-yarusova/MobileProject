package com.anyarusova.smarthouse.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anyarusova.smarthouse.model.CameraDevice

@Composable
fun CameraView(device: CameraDevice, onEditDevice: (CameraDevice) -> Unit) {
    var isRecording by remember { mutableStateOf(device.isRecording) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Camera Settings", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Recording: ")
            Switch(checked = isRecording, onCheckedChange = { isRecording = it })
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val updatedDevice = device.copy(isRecording = isRecording)
            onEditDevice(updatedDevice)
        }) {
            Text(text = "Save")
        }
    }
}
