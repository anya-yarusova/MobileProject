package com.anyarusova.smarthouse.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anyarusova.smarthouse.model.LightDevice

@Composable
fun LightView(device: LightDevice, onEditDevice: (LightDevice) -> Unit) {
    var brightness by remember { mutableStateOf(device.brightness) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Light Settings", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Brightness: $brightness")
        Slider(
            value = brightness,
            onValueChange = { brightness = it },
            valueRange = 0f..100f
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val updatedDevice = device.copy(brightness = brightness)
            onEditDevice(updatedDevice)
        }) {
            Text(text = "Save")
        }
    }
}
