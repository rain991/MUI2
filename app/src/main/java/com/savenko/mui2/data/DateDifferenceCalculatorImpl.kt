package com.example.mui1.data

import android.util.Log
import java.time.Duration
import java.time.Instant
import java.util.Date
import kotlin.math.absoluteValue

class DateDifferenceCalculatorImpl : DateDifferenceCalculator {
    override fun calculateDateDifference(
        date1: Long,
        date2: Long
    ): DateDifferenceCalculation {
        try {
            val duration =
                Duration.between(Instant.ofEpochMilli(date1), Instant.ofEpochMilli(date2))
            val weeks = duration.toDays() / 7
            val days = duration.toDays() % 7
            val hours = duration.toHours() % 24
            val minutes = duration.toMinutes() % 60
            val seconds = duration.seconds % 60
            return DateDifferenceCalculation(
                InputDate1 = date1.absoluteValue,
                InputDate2 = date2.absoluteValue,
                WeeksDifference = weeks.toInt().absoluteValue,
                DaysDifference = days.toInt().absoluteValue,
                HoursDifference = (hours.toInt() + 1).absoluteValue,
                MinutesDifference = minutes.toInt().absoluteValue,
                SecondsDifference = seconds.toInt().absoluteValue
            )
        } catch (e: Exception) {
            Log.e(DateDifferenceCalculatorImpl::class.java.simpleName, e.message, e)
            return DateDifferenceCalculation(
                InputDate1 = date1,
                InputDate2 = date2,
                WeeksDifference = 0,
                DaysDifference = 0,
                HoursDifference = 0,
                MinutesDifference = 0,
                SecondsDifference = 0
            )
        }
    }
}