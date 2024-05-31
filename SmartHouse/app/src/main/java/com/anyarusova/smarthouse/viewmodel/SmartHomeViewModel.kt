package com.anyarusova.smarthouse.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.anyarusova.smarthouse.infrastracture.SmartHomeDatabase
import com.anyarusova.smarthouse.model.ThermostatDevice
import com.anyarusova.smarthouse.model.CameraDevice
import com.anyarusova.smarthouse.model.DeviceType
import com.anyarusova.smarthouse.model.LightDevice
import com.anyarusova.smarthouse.model.SmartDevice
import kotlinx.coroutines.launch

class SmartHomeViewModel(application: Application) : AndroidViewModel(application) {
    private val smartDeviceDao = SmartHomeDatabase.getDatabase(application).smartDeviceDao()
    val devices = mutableStateListOf<SmartDevice>()

    init {
        viewModelScope.launch {
            for (device in smartDeviceDao.getAllDevices()) {
                run {
                    when (device.type) {
                        DeviceType.LIGHT -> devices.add(
                            LightDevice(
                                device.id,
                                device.name,
                                device.type,
                                device.status
                            )
                        )

                        DeviceType.THERMOSTAT -> devices.add(
                            ThermostatDevice(
                                device.id,
                                device.name,
                                device.type,
                                device.status
                            )
                        )

                        DeviceType.CAMERA -> devices.add(
                            CameraDevice(
                                device.id,
                                device.name,
                                device.type,
                                device.status
                            )
                        )
                    }
                }
            }
        }
    }

    fun addDevice(device: SmartDevice) {
        viewModelScope.launch {
            smartDeviceDao.insertDevice(device)
            devices.add(device)
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
