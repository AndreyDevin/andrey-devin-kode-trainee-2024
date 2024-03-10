package com.example.kode2024_test.ui.entity

import com.example.kode2024_test.data.dto.Employee

sealed interface Intent {

    sealed class DataOptions: Intent {
        data object TotalList: DataOptions()
        class SingleDepartment(val department: Department): DataOptions()
        class Search(val searchField: String): DataOptions()
        class Details(val employee: Employee): DataOptions()
    }

    enum class SortingOption: Intent {
        ByAlphabet,
        ByBrithDay
    }

    data object Refresh: Intent
}