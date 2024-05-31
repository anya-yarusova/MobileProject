package com.anyarusova.smarthouse.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anyarusova.smarthouse.model.ThermostatDevice

@Composable
fun ThermostatView(device: ThermostatDevice, onEditDevice: (ThermostatDevice) -> Unit) {
    var temperature by remember { mutableStateOf(device.temperature) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Thermostat Settings", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Temperature: $temperature°C")
        Slider(
            value = temperature,
            onValueChange = { temperature = it },
            valueRange = 16f..30f
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val updatedDevice = device.copy(temperature = temperature)
            onEditDevice(updatedDevice)
        }) {
            Text(text = "Save")
        }
    }
}
