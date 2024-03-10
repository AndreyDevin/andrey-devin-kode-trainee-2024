package com.example.kode2024_test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kode2024_test.data.UseCase
import com.example.kode2024_test.ui.entity.Department
import com.example.kode2024_test.ui.entity.Intent
import com.example.kode2024_test.ui.entity.SortingOption
import com.example.kode2024_test.ui.entity.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: UseCase
): ViewModel() {

    private var department = Department.All
    private var searchField = ""
    private var sortingOption = SortingOption.ByAlphabet

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val state = _state.asStateFlow()

    init { updateData() }

    fun executeIntent(intent: Intent) {
        when(intent) {
            is Intent.DepartmentSelect -> department = intent.department
            is Intent.Search -> searchField = intent.searchField
            is Intent.SortingSelect -> sortingOption = intent.option
            is Intent.Details -> {
                getDetails(intent.id)
                return
            }
            Intent.Refresh -> {}
        }
        updateData()
    }

    private fun updateData() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = UiState.Loading

            val data = useCase.updateData(department, searchField, sortingOption)

            _state.value = UiState.EmployeesList(
                list = data,
                department = department,
                sortingOption = sortingOption
            )
        }
    }

    private fun getDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = useCase.getDetails(id)
            if (data.isNotEmpty()) _state.value = UiState.EmployeeDetails(data.first())
            else _state.value = UiState.EmptyList
        }
    }
}