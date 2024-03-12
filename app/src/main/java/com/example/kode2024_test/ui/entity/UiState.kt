package com.example.kode2024_test.ui.entity

import com.example.kode2024_test.data.dto.Employee

data class UiState (
    val userChoice: UserChoice,
    val data: Data
)

data class UserChoice(
    val department: Department,
    val searchField: String,
    val sortingOption: SortingOption
)

sealed class Data {
    data class EmployeesList(val list: List<Employee>): Data()
    data class EmployeeDetails(val employee: Employee): Data()
    data object EmptyList: Data()
    data object Loading: Data()
    data object Error: Data()
}
