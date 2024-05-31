package com.anyarusova.smarthouse

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SmartHomeViewModel(application: Application) : AndroidViewModel(application) {
    private val smartDeviceDao = SmartHomeDatabase.getDatabase(application).smartDeviceDao()
    val devices = mutableStateListOf<SmartDevice>()

    init {
        viewModelScope.launch {
            devices.addAll(smartDeviceDao.getAllDevices())
        }
    }

    fun addDevice(device: SmartDevice) {
        viewModelScope.launch {
            smartDeviceDao.insertDevice(device)
            devices.add(device.copy())
        }
    }

    fun removeDevice(device: SmartDevice) {
        viewModelScope.launch {
            smartDeviceDao.deleteDevice(device)
            devices.remove(device)
        }
    }

    fun editDevice(newDevice: SmartDevice) {
        viewModelScope.launch {
            smartDeviceDao.updateDevice(newDevice)
            val index = devices.indexOfFirst { it.id == newDevice.id }
            if (index != -1) {
                devices[index] = newDevice
            }
        }
    }
}
