package com.anyarusova.smarthouse

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EditDeviceScreen(device: SmartDevice, onEditDevice: (SmartDevice) -> Unit, onDeleteDevice: (SmartDevice) -> Unit, onCancel: () -> Unit) {
    var name by remember { mutableStateOf(device.name) }
    var type by remember { mutableStateOf(device.type) }
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

        OutlinedTextField(
            value = type,
            onValueChange = { type = it },
            label = { Text("Device Type") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Button(onClick = {
                val updatedDevice = device.copy(name = name, type = type, status = status)
                onEditDevice(updatedDevice)
            }, modifier = Modifier.weight(1f)) {
                Text(text = "Save Changes")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onCancel, modifier = Modifier.weight(1f)) {
                Text(text = "Cancel")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onDeleteDevice(device) }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
            Text(text = "Delete Device", color = Color.White)
        }
    }
}
