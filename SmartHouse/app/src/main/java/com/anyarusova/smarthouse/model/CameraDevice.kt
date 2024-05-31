package com.anyarusova.smarthouse.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "smart_devices")
data class CameraDevice(
    @PrimaryKey(autoGenerate = true) override val id: Int = 0,
    override val name: String,
    override val type: DeviceType = DeviceType.CAMERA,
    override val status: Boolean = false,
    val isRecording: Boolean = false
) : SmartDevice(id, name, type, status)
