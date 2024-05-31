package com.anyarusova.smarthouse.ui.view

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
import com.anyarusova.smarthouse.model.SmartDevice
import com.anyarusova.smarthouse.viewmodel.SmartHomeViewModel
import kotlinx.coroutines.launch

@Composable
fun SmartHomeScreen(viewModel: SmartHomeViewModel) {
    var selectedDevice by remember { mutableStateOf<SmartDevice?>(null) }
    var isAddingDevice by remember { mutableStateOf(false) }

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
    } else if (selectedDevice != null) {
        EditDeviceScreen(
            device = selectedDevice!!,
            onEditDevice = { device ->
                coroutineScope.launch {
                    viewModel.editDevice(device)
                    selectedDevice = null
                }
            },
            onDeleteDevice = { device ->
                coroutineScope.launch {
                    viewModel.removeDevice(device)
                    selectedDevice = null
                }
            },
            onCancel = {
                selectedDevice = null
            }
        )
    } else {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            DeviceList(
                devices = viewModel.devices,
                onEditDevice = { device ->
                    selectedDevice = device
                },
                onToggleDeviceStatus = { device ->
                    coroutineScope.launch {
                        val updatedDevice =
                            SmartDevice(device.id, device.name, device.type, !device.status);
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
