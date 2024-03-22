package com.example.kode2024_test

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kode2024_test.domain.UseCase
import com.example.kode2024_test.domain.entity.Data
import com.example.kode2024_test.domain.entity.Department
import com.example.kode2024_test.domain.entity.Intent
import com.example.kode2024_test.domain.entity.SortingOption
import com.example.kode2024_test.domain.entity.UiState
import com.example.kode2024_test.domain.entity.UserChoice
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
    private var detailsID = ""

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
            is Intent.Details -> detailsID = intent.id
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

            val data =
                if (detailsID.isNotEmpty()) useCase.updateData(detailsID)
                else useCase.updateData(department, searchField, sortingOption)

            _state.value = UiState(
                UserChoice(department, searchField, sortingOption, lazyListState),
                data
            )

            detailsID = ""
        }
    }
}