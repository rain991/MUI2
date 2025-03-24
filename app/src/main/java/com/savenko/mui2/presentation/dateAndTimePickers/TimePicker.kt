package com.savenko.mui2.presentation.dateAndTimePickers

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    isVisible: Boolean,
    currentTimeCalendar: Calendar,
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    val timePickerState = rememberTimePickerState(
        initialHour = currentTimeCalendar.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTimeCalendar.get(Calendar.MINUTE),
        is24Hour = true,
    )
    if (isVisible) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Select Time") },
            text = {
                Column {
                    TimeInput(state = timePickerState)
                }
            },
            confirmButton = {
                TextButton(onClick = { onConfirm(timePickerState) }) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        )
    }
}
