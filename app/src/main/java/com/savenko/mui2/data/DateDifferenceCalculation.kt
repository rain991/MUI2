package com.example.mui1.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "dateDifferenceCalculation"
)
data class DateDifferenceCalculation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "InputDate1")
    val InputDate1: Long,
    @ColumnInfo(name = "InputDate2")
    val InputDate2: Long,
    @ColumnInfo(name = "WeeksDifference")
    val WeeksDifference: Int,
    @ColumnInfo(name = "DaysDifference")
    val DaysDifference: Int,
    @ColumnInfo(name = "HoursDifference")
    val HoursDifference: Int,
    @ColumnInfo(name = "MinutesDifference")
    val MinutesDifference: Int,
    @ColumnInfo(name = "SecondsDifference")
    val SecondsDifference: Int,
)