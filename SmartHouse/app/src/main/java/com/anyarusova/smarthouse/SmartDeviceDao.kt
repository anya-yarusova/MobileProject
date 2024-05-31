package com.anyarusova.smarthouse

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface SmartDeviceDao {
    @Query("SELECT * FROM smart_devices")
    suspend fun getAllDevices(): List<SmartDevice>

    @Insert
    suspend fun insertDevice(device: SmartDevice)

    @Update
    suspend fun updateDevice(device: SmartDevice)

    @Delete
    suspend fun deleteDevice(device: SmartDevice)
}