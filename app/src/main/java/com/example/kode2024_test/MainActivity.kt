package com.example.kode2024_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.kode2024_test.ui.composable.Details
import com.example.kode2024_test.ui.composable.EmployeesList
import com.example.kode2024_test.ui.entity.Data
import com.example.kode2024_test.ui.entity.Intent
import com.example.kode2024_test.ui.entity.UiState
import com.example.kode2024_test.ui.theme.Kode2024_testTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel by viewModel<MainViewModel>()

        /*lifecycleScope.launch {
            //viewModel.executeIntent(Intent.DepartmentSelect(Department.ANDROID))
            delay(2000)
            //viewModel.executeIntent(Intent.SortingSelect(SortingOption.ByBrithDay))
            //delay(2000)
            //viewModel.executeIntent(Intent.Search("ari"))
            //delay(4000)
            //viewModel.executeIntent(Intent.Search(""))
            //delay(10000)
            viewModel.executeIntent(Intent.Details("514bec78-d65d-4140-81d7-e23fb3fc3ba8"))
            //delay(5000)
            //viewModel.executeIntent(Intent.OnBackPressed)
        }*/


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
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    state: UiState,
    intent: (Intent) -> Unit
) {
    state.data.also { data ->
        when (data) {

            is Data.Loading -> Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }

            is Data.EmployeesList -> EmployeesList(
                list = data.list,
                onItemClick = intent
            )
            else -> {}
        }
    }
}