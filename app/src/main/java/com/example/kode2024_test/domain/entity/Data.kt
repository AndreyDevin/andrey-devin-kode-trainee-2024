package com.example.kode2024_test.domain.entity

sealed class Data {
    data class EmployeesList(val list: List<Employee>): Data()
    data class EmployeeDetails(val employee: Employee): Data()
    data class Error(val errorText: String) : Data(), DataState
    data object EmptyList: Data()
    data object Loading: Data(), DataState
}

interface DataState