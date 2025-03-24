package com.savenko.mui2.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.savenko.mui2.presentation.dateAndTimePickers.DatePicker
import com.savenko.mui2.presentation.dateAndTimePickers.DatePickerModal
import com.savenko.mui2.presentation.dateAndTimePickers.TimePickerDialog
import java.util.Calendar
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
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
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                val formattedDate = screenState.value.date1.formatDateAndTime()

                Text(text = formattedDate.take(9), modifier = Modifier.clickable {
                    viewModel.setTime1PickerVisibility(true)
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
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                val formattedDate = screenState.value.date2.formatDateAndTime()

                Text(text = formattedDate.take(9), modifier = Modifier.clickable {
                    viewModel.setTime2PickerVisibility(true)
                }, style = MaterialTheme.typography.headlineSmall, textAlign = TextAlign.Center)

                Text(
                    text = formattedDate.drop(9),
                    modifier = Modifier.clickable { viewModel.setDate2PickerVisibility(true) },
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }
        }

        if (screenState.value.isTime1PickerVisible || screenState.value.isTime2PickerVisible) {
            val timeCalendar = Calendar.getInstance().apply {
                this.time =
                    if (screenState.value.isTime1PickerVisible) screenState.value.date1 else screenState.value.date2
            }
            TimePickerDialog(
                currentTimeCalendar = timeCalendar,
                isVisible = true,
                onConfirm = { timePickerState ->
                    val calendar = Calendar.getInstance().apply {
                        timeCalendar
                    }
                    calendar.set(Calendar.HOUR_OF_DAY, timePickerState.hour)
                    calendar.set(Calendar.MINUTE, timePickerState.minute)
                    if (screenState.value.isTime1PickerVisible) {
                        viewModel.setDate1(calendar.time)
                    } else {
                        viewModel.setDate2(calendar.time)
                    }
                    viewModel.hideAllPickers()
                    viewModel.calculateDateDifference()
                }) {
                viewModel.hideAllPickers()
            }

        }

        if (screenState.value.isDate1PickerVisible || screenState.value.isDate2PickerVisible) {
            val selectedTimeMillis = if (screenState.value.isDate1PickerVisible) {
                screenState.value.date1
            } else {
                screenState.value.date2
            }
            DatePicker(
                isVisible = true,
                selectedTimeMillis = selectedTimeMillis.time,
                onDateSelected = { selectedDate ->
                    selectedDate?.let {
                        if (screenState.value.isDate1PickerVisible) {
                            viewModel.setDate1(Date(it))
                        } else {
                            viewModel.setDate2(Date(it))
                        }
                        viewModel.hideAllPickers()
                        viewModel.calculateDateDifference()
                    }
                }) {
                viewModel.hideAllPickers()
            }
        }
        if(screenState.value.dateDifferenceCalculation != null){
            Spacer(modifier = Modifier.height(16.dp))
        }
        AnimatedVisibility(screenState.value.dateDifferenceCalculation != null) {
            DateDifferenceResultComposable(screenState.value.dateDifferenceCalculation!!)
        }
    }
}
