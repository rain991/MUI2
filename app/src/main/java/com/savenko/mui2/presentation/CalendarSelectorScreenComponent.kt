package com.savenko.mui2.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mui1.data.CalendarCalculatorViewModel
import com.example.mui1.data.formatDateAndTime

@Composable
internal fun CalendarSelectorScreenComponent(
    viewModel: CalendarCalculatorViewModel
) {
    val screenState = viewModel.dateDifferenceScreenState.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Date 1", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(8.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                val formattedDate = screenState.value.date1.formatDateAndTime()

                Text(text = formattedDate.take(9), modifier = Modifier.clickable {

                }, style = MaterialTheme.typography.headlineSmall, textAlign = TextAlign.Center)

                Text(
                    text = formattedDate.drop(9),
                    modifier = Modifier.clickable { viewModel.setDate1PickerVisibility(true) },
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { viewModel.setDate2PickerVisibility(true) },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Date 2", style = MaterialTheme.typography.headlineMedium)
            Spacer(Modifier.height(8.dp))
            Text(
                text = screenState.value.date2.formatDateAndTime(),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )
        }

        AnimatedVisibility(screenState.value.dateDifferenceCalculation != null) {
            DateDifferenceResultComposable(screenState.value.dateDifferenceCalculation!!)
        }
    }
}
