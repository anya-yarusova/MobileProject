package com.anyarusova.smarthouse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SmartDevice::class], version = 1)
abstract class SmartHomeDatabase : RoomDatabase() {
    abstract fun smartDeviceDao(): SmartDeviceDao

    companion object {
        @Volatile
        private var INSTANCE: SmartHomeDatabase? = null

        fun getDatabase(context: Context): SmartHomeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SmartHomeDatabase::class.java,
                    "smart_home_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}