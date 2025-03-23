package com.savenko.mui2.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mui1.data.DateDifferenceCalculation

@Composable
fun DateDifferenceResultComposable(dateDifferenceCalculation: DateDifferenceCalculation) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Difference is")

        Text(text = "Weeks: ${dateDifferenceCalculation.WeeksDifference}")

        Text(text = "Days: ${dateDifferenceCalculation.DaysDifference}")

        Text(text = "Hours: ${dateDifferenceCalculation.HoursDifference}")

        Text(text = "Minutes: ${dateDifferenceCalculation.MinutesDifference}")

        Text(text = "Seconds: ${dateDifferenceCalculation.SecondsDifference}")
    }
}