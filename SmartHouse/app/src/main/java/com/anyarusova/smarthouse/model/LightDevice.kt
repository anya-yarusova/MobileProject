package com.anyarusova.smarthouse.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "smart_devices")
data class LightDevice(
    @PrimaryKey(autoGenerate = true) override val id: Int = 0,
    override val name: String,
    override val type: DeviceType = DeviceType.LIGHT,
    override val status: Boolean = false,
    val brightness: Float = 50f
) : SmartDevice(id, name, type, status)
