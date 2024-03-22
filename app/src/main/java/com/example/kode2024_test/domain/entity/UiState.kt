package com.example.kode2024_test.domain.entity

import androidx.compose.foundation.lazy.LazyListState

data class UiState (
    val userChoice: UserChoice,
    val data: Data
)

data class UserChoice(
    val department: Department,
    val searchField: String,
    val sortingOption: SortingOption,
    val lazyListState: LazyListState
)

sealed class Data {
    data class EmployeesList(val list: List<Employee>): Data()
    data class EmployeeDetails(val employee: Employee): Data()
    data object EmptyList: Data()
    data object Loading: Data()
    data object Error: Data()
}
