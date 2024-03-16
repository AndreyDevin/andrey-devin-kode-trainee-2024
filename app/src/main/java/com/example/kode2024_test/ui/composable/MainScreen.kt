package com.example.kode2024_test.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.kode2024_test.ui.entity.Data
import com.example.kode2024_test.ui.entity.Intent
import com.example.kode2024_test.ui.entity.UiState


@Composable
fun MainScreen(
    state: UiState,
    intent: (Intent) -> Unit
) {
    val tabState = remember { mutableIntStateOf(state.userChoice.department.ordinal) }

    Column {

        DepartmentsTabRow(tabState = tabState, onTabClick = intent)

        state.data.also { data ->
            when (data) {

                is Data.Loading -> Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }

                is Data.EmployeesList -> EmployeesList(
                    list = data.list,
                    lazyListState = state.userChoice.lazyListState,
                    onItemClick = intent
                )

                else -> {}
            }
        }
    }
}