package com.example.kode2024_test.ui.entity

import com.example.kode2024_test.data.dto.Employee

sealed class UiState {

    class EmployeesList(
        val list: List<Employee>,
        val department: Department,
        val sortingOption: SortingOption
    ): UiState()

    class EmployeeDetails(val employee: Employee): UiState()

    data object EmptyList: UiState()
    data object Loading: UiState()
    data object Error: UiState()
}