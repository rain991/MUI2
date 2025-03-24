package com.savenko.mui2.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
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

        HorizontalDivider(modifier = Modifier.fillMaxWidth(0.65f))
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Difference is",
            style = androidx.compose.material3.MaterialTheme.typography.headlineMedium
        )

        Text(
            text = "Weeks: ${dateDifferenceCalculation.WeeksDifference}",
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Days: ${dateDifferenceCalculation.DaysDifference}",
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Hours: ${dateDifferenceCalculation.HoursDifference}",
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Minutes: ${dateDifferenceCalculation.MinutesDifference}",
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Seconds: ${dateDifferenceCalculation.SecondsDifference}",
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall
        )
    }
}