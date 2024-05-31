package com.anyarusova.smarthouse.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "smart_devices")
open class SmartDevice(
    @PrimaryKey(autoGenerate = true) open val id: Int = 0,
    open val name: String,
    open val type: DeviceType,
    open val status: Boolean
)