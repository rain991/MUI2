package com.example.mui1.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.mui1.presentation.DateDifferenceScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

class CalendarCalculatorViewModel(
    private val dateDifferenceCalculationDao: DateDifferenceCalculationDao,
    private val dateDifferenceCalculator: DateDifferenceCalculator
) : ViewModel() {
    private val _dateDifferenceScreenState = MutableStateFlow(
        DateDifferenceScreenState(
            date1 = Date(),
            date2 = Date(),
            isDate1PickerVisible = false,
            isTime1PickerVisible = false,
            isDate2PickerVisible = false,
            isTime2PickerVisible = false,
            dateDifferenceCalculation = null,
            error = null
        )
    )
    val dateDifferenceScreenState = _dateDifferenceScreenState.asStateFlow()

    init {
        viewModelScope.launch {
            setDate1(Date(System.currentTimeMillis()))
            setDate2(Date(System.currentTimeMillis() + 7 * 1000 * 60 * 60 * 24))
        }
    }


    fun calculateDateDifference() {
        val date1 = _dateDifferenceScreenState.value.date1
        val date2 = _dateDifferenceScreenState.value.date2

        setDateDifferenceCalculation(
            dateDifferenceCalculator.calculateDateDifference(
                date1 = date1.time,
                date2 = date2.time
            )
        )
        viewModelScope.launch {
            dateDifferenceCalculationDao.insertItem(dateDifferenceScreenState.value.dateDifferenceCalculation!!)
        }
    }

    fun setDate1(value: Date) {
        _dateDifferenceScreenState.update { _dateDifferenceScreenState.value.copy(date1 = value) }
    }

    fun setDate2(value: Date) {
        _dateDifferenceScreenState.update { _dateDifferenceScreenState.value.copy(date2 = value) }
    }

    fun setDate1PickerVisibility(value: Boolean) {
        _dateDifferenceScreenState.update {
            _dateDifferenceScreenState.value.copy(
                isDate1PickerVisible = value
            )
        }
    }

    fun setTime1PickerVisibility(value: Boolean) {
        _dateDifferenceScreenState.update {
            _dateDifferenceScreenState.value.copy(
                isTime1PickerVisible = value
            )
        }
    }

    fun setDate2PickerVisibility(value: Boolean) {
        _dateDifferenceScreenState.update {
            _dateDifferenceScreenState.value.copy(
                isDate2PickerVisible = value
            )
        }
    }

    fun setTime2PickerVisibility(value: Boolean) {
        _dateDifferenceScreenState.update {
            _dateDifferenceScreenState.value.copy(
                isTime2PickerVisible = value
            )
        }
    }

    private fun setDateDifferenceCalculation(value: DateDifferenceCalculation) {
        _dateDifferenceScreenState.update {
            _dateDifferenceScreenState.value.copy(
                dateDifferenceCalculation = value
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                val dateDifferenceCalculationDao =
                    DateDifferenceCalculationDB.getInstance(application).dateDifferenceCalculationDao
                return CalendarCalculatorViewModel(
                    dateDifferenceCalculationDao,
                    DateDifferenceCalculatorImpl()
                ) as T
            }
        }
    }
}