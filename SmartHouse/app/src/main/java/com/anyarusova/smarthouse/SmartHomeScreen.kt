package com.anyarusova.smarthouse

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun SmartHomeScreen(viewModel: SmartHomeViewModel) {
    var selectedDevice by remember { mutableStateOf<SmartDevice?>(null) }
    var isAddingDevice by remember { mutableStateOf(false) }
    var isEditingDevice by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    if (isAddingDevice) {
        AddDeviceScreen(
            onAddDevice = { device ->
                coroutineScope.launch {
                    viewModel.addDevice(device)
                    isAddingDevice = false
                }
            },
            onCancel = { isAddingDevice = false }
        )
    } else if (selectedDevice != null && isEditingDevice) {
        EditDeviceScreen(
            device = selectedDevice!!,
            onEditDevice = { device ->
                coroutineScope.launch {
                    viewModel.editDevice(device)
                    selectedDevice = null
                    isEditingDevice = false
                }
            },
            onDeleteDevice = { device ->
                coroutineScope.launch {
                    viewModel.removeDevice(device)
                    selectedDevice = null
                    isEditingDevice = false
                }
            },
            onCancel = {
                selectedDevice = null
                isEditingDevice = false
            }
        )
    } else {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            DeviceList(
                devices = viewModel.devices,
                onEditDevice = { device ->
                    selectedDevice = device
                    isEditingDevice = true
                },
                onToggleDeviceStatus = { device ->
                    coroutineScope.launch {
                        val updatedDevice = device.copy(status = !device.status)
                        viewModel.editDevice(updatedDevice)
                    }
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { isAddingDevice = true }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Add Device")
            }
        }
    }
}
