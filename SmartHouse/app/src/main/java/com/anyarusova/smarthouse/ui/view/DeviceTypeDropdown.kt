package com.anyarusova.smarthouse.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.anyarusova.smarthouse.model.DeviceType

@Composable
fun DeviceTypeDropdown(selectedType: DeviceType, onTypeSelected: (DeviceType) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val types = DeviceType.values()

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
            Text(text = selectedType.name)
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            types.forEach { type ->
                DropdownMenuItem(onClick = {
                    onTypeSelected(type)
                    expanded = false
                }, text = { Text(text = type.name) })
            }
        }
    }
}
