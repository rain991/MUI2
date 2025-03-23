package com.example.mui1.data

interface DateDifferenceCalculator {
    fun calculateDateDifference(date1 : Long, date2 : Long) : DateDifferenceCalculation
}