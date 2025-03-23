package com.example.mui1.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [DateDifferenceCalculation::class],
    version = 1
)
abstract class DateDifferenceCalculationDB : RoomDatabase() {
    abstract val dateDifferenceCalculationDao : DateDifferenceCalculationDao

    companion object {
        private var INSTANCE: DateDifferenceCalculationDB? = null
        fun getInstance(context: Context): DateDifferenceCalculationDB {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): DateDifferenceCalculationDB {
            return Room.databaseBuilder(
                context.applicationContext,
                DateDifferenceCalculationDB::class.java, "main.db"
            ).build()
        }
    }
}