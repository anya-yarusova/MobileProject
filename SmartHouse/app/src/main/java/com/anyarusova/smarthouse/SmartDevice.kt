package com.anyarusova.smarthouse

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "smart_devices")
data class SmartDevice(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val type: String,
    val status: Boolean
)