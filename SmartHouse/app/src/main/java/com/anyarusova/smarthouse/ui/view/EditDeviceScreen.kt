package com.anyarusova.smarthouse.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.anyarusova.smarthouse.model.CameraDevice
import com.anyarusova.smarthouse.model.LightDevice
import com.anyarusova.smarthouse.model.SmartDevice
import com.anyarusova.smarthouse.model.ThermostatDevice

@Composable
fun EditDeviceScreen(device: SmartDevice, onEditDevice: (SmartDevice) -> Unit, onDeleteDevice: (SmartDevice) -> Unit, onCancel: () -> Unit) {
    var name by remember { mutableStateOf(device.name) }
    var status by remember { mutableStateOf(device.status) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Edit Device", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Device Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Status: ")
            Switch(checked = status, onCheckedChange = { status = it })
            Text(text = if (status) "On" else "Off")
        }
        Spacer(modifier = Modifier.height(16.dp))

        when (device) {
            is LightDevice -> LightView(device.copy(name = name, status = status), onEditDevice)
            is ThermostatDevice -> ThermostatView(device.copy(name = name, status = status), onEditDevice)
            is CameraDevice -> CameraView(device.copy(name = name, status = status), onEditDevice)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onDeleteDevice(device) }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
            Text(text = "Delete Device", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onCancel) {
            Text(text = "Cancel")
        }
    }
}

