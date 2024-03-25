package com.example.kode2024_test.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.kode2024_test.domain.entity.Data
import com.example.kode2024_test.domain.entity.Intent
import com.example.kode2024_test.domain.entity.UiState

@Composable
fun MainScreen(
    state: UiState,
    intent: (Intent) -> Unit
) {
    val tabState = remember { mutableIntStateOf(state.options.department.ordinal) }

    Column(modifier = Modifier) {

        Search(state = state.options, intent = intent)

        DepartmentsTabRow(tabState = tabState, onTabClick = intent)

        if (state.data is Data.EmployeesList) EmployeesList(
            sortingOption = state.options.sortingOption,
            list = state.data.list,
            lazyListState = state.options.lazyListState,
            onItemClick = intent
        )
        else SearchResultNothing()
    }
}