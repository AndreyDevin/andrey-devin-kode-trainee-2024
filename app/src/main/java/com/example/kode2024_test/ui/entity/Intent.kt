package com.example.kode2024_test.ui.entity

sealed class Intent {
    class DepartmentSelect(val department: Department) : Intent()
    class Search(val searchField: String) : Intent()
    class Details(val id: String) : Intent()
    class SortingSelect(val option: SortingOption) : Intent()
    data object Refresh : Intent()
}