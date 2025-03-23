package com.savenko.mui2.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mui1.data.CalendarCalculatorViewModel
import com.example.mui1.presentation.CalendarSelectorScreen
import com.savenko.mui2.presentation.theme.MUI2Theme
import kotlin.getValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val  viewModel : CalendarCalculatorViewModel by viewModels { CalendarCalculatorViewModel.Factory }
        enableEdgeToEdge()
        setContent {
            MUI2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalendarSelectorScreen(paddingValues = innerPadding, viewModel = viewModel)
                }
            }
        }
    }
}
