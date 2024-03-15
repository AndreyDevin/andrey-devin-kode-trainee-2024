package com.example.kode2024_test

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kode2024_test.data.UseCase
import com.example.kode2024_test.ui.entity.Data
import com.example.kode2024_test.ui.entity.Department
import com.example.kode2024_test.ui.entity.Intent
import com.example.kode2024_test.ui.entity.SortingOption
import com.example.kode2024_test.ui.entity.UiState
import com.example.kode2024_test.ui.entity.UserChoice
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
    private var lazyListState = LazyListState()

    private val _state: MutableStateFlow<UiState> =
        MutableStateFlow(
            UiState(
                UserChoice(department, searchField, sortingOption, lazyListState),
                Data.Loading
            )
        )
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
            Intent.OnBackPressed -> {
                updateData()
                return
            }
            Intent.Refresh -> {}
        }
        lazyListState = LazyListState()
        updateData()
    }

    private fun updateData() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = UiState(
                UserChoice(department, searchField, sortingOption, lazyListState),
                Data.Loading
            )

            val data = useCase.updateData(department, searchField, sortingOption)

            if (data.isNotEmpty()) _state.value = UiState(
                UserChoice(department, searchField, sortingOption, lazyListState),
                Data.EmployeesList(data)
            )
            else _state.value = UiState(
                UserChoice(department, searchField, sortingOption, lazyListState),
                Data.EmptyList
            )
        }
    }

    private fun getDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = UiState(
                UserChoice(department, searchField, sortingOption, lazyListState),
                Data.Loading
            )

            val data = useCase.getDetails(id)
            if (data.isNotEmpty()) _state.value =
                UiState(
                    UserChoice(department, searchField, sortingOption, lazyListState),
                    Data.EmployeeDetails(data.first())
                )
            else _state.value = UiState(
                UserChoice(department, searchField, sortingOption, lazyListState),
                Data.EmptyList
            )
        }
    }
}