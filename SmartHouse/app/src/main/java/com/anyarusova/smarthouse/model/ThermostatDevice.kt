package com.anyarusova.smarthouse.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anyarusova.smarthouse.model.DeviceType
import com.anyarusova.smarthouse.model.SmartDevice

@Entity(tableName = "smart_devices")
data class ThermostatDevice(
    @PrimaryKey(autoGenerate = true) override val id: Int = 0,
    override val name: String,
    override val type: DeviceType = DeviceType.THERMOSTAT,
    override val status: Boolean = false,
    val temperature: Float = 20f
) : SmartDevice(id, name, type, status)
