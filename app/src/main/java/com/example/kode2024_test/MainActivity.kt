package com.example.kode2024_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.kode2024_test.domain.entity.Data
import com.example.kode2024_test.domain.entity.Intent
import com.example.kode2024_test.ui.composable.Details
import com.example.kode2024_test.ui.composable.ErrorButton
import com.example.kode2024_test.ui.composable.MainScreen
import com.example.kode2024_test.ui.theme.Kode2024_testTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel by viewModel<MainViewModel>()

        lifecycleScope.launch {
            viewModel.state.collect { state ->

                setContent {
                    Kode2024_testTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            if (state.data is Data.EmployeeDetails) Details(
                                employee = state.data.employee,
                                onBackPressed = { viewModel.executeIntent(Intent.OnBackPressed) }
                            )
                            else MainScreen(
                                state = state,
                                intent = viewModel::executeIntent
                            )
                            ErrorButton(
                                state = state,
                                intent = {
                                    viewModel.executeIntent(
                                        Intent.ErrorGenerate(!state.userChoice.errorGenerate)
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}