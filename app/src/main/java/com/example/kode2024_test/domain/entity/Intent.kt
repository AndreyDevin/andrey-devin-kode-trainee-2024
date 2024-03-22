package com.example.kode2024_test.domain.entity

sealed class Intent {
    class DepartmentSelect(val department: Department) : Intent()
    class Search(val searchField: String) : Intent()
    class Details(val id: String) : Intent()
    class SortingSelect(val option: SortingOption) : Intent()
    class ErrorGenerate(val withError: Boolean) : Intent()
    data object Refresh : Intent()
    data object OnBackPressed : Intent()
}