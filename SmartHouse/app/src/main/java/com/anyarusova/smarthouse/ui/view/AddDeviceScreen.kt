package com.anyarusova.smarthouse.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anyarusova.smarthouse.model.CameraDevice
import com.anyarusova.smarthouse.model.DeviceType
import com.anyarusova.smarthouse.model.LightDevice
import com.anyarusova.smarthouse.model.SmartDevice
import com.anyarusova.smarthouse.model.ThermostatDevice

@Composable
fun AddDeviceScreen(onAddDevice: (SmartDevice) -> Unit, onCancel: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var type by remember { mutableStateOf(DeviceType.LIGHT) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Add New Device", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Device Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        DeviceTypeDropdown(type, onTypeSelected = { type = it })
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = {
                val newDevice: SmartDevice = when (type) {
                    DeviceType.LIGHT -> LightDevice(name = name)
                    DeviceType.THERMOSTAT -> ThermostatDevice(name = name)
                    DeviceType.CAMERA -> CameraDevice(name = name)
                }
                onAddDevice(newDevice)
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Add Device")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onCancel, modifier = Modifier.weight(1f)) {
                Text(text = "Cancel")
            }
        }
    }
}

