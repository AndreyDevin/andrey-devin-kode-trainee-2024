package com.example.kode2024_test.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kode2024_test.data.dto.Employee
import com.example.kode2024_test.ui.entity.Intent

@Composable
fun EmployeesList(
    list: List<Employee>,
    lazyListState: LazyListState,
    onItemClick: (Intent.Details) -> Unit
) {
    LazyColumn(
        modifier = Modifier.padding(start = 14.dp),
        state = lazyListState
    ) {
        items(count = list.size) {index ->
            EmployeesListItem(
                employee = list[index],
                clickListener = onItemClick
            )
        }
    }
}