package com.example.mui1.presentation

import com.example.mui1.data.DateDifferenceCalculation
import java.util.Date

data class DateDifferenceScreenState(
    val date1 : Date,
    val date2 : Date,
    val dateDifferenceCalculation : DateDifferenceCalculation?,
    val error : CalendarSelectorScreenErrors?
)
