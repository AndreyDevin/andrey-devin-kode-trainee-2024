package com.example.kode2024_test

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kode2024_test.domain.UseCase
import com.example.kode2024_test.domain.entity.Data
import com.example.kode2024_test.domain.entity.Department
import com.example.kode2024_test.domain.entity.Info
import com.example.kode2024_test.domain.entity.Intent
import com.example.kode2024_test.domain.entity.SortingOption
import com.example.kode2024_test.domain.entity.UiState
import com.example.kode2024_test.domain.entity.Options
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
    private var withError = false
    private var data: Data = Data.EmptyList
    private var info: Info? = null

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(stateCreate(Data.Loading))
    val state = _state.asStateFlow()

    init { updateData() }

    fun executeIntent(intent: Intent) {
        when(intent) {
            is Intent.DepartmentSelect -> department = intent.department
            is Intent.Search -> searchField = intent.searchField
            is Intent.SortingSelect -> sortingOption = intent.option
            is Intent.Details -> detailsID = intent.id
            is Intent.ErrorGenerate -> {
                withError = intent.withError
                return
            }
            Intent.OnBackPressed -> {}
            Intent.Refresh -> {}
        }

        if (intent !is Intent.OnBackPressed && intent !is Intent.Details) {
            lazyListState = LazyListState()
        }

        updateData()
    }

    private fun updateData() {
        viewModelScope.launch(Dispatchers.IO) {

            _state.value = stateCreate(Data.Loading)

            val data =
                if (detailsID.isNotEmpty()) useCase.updateData(detailsID, withError)
                else useCase.updateData(department, searchField, sortingOption, withError)

            _state.value = stateCreate(data)

            detailsID = ""
        }
    }

    private fun stateCreate(newData: Data): UiState {
        if(newData is Info) info = newData
        else {
            data = newData
            info = null
        }

        return UiState(
                Options(department, searchField, sortingOption, lazyListState, withError),
                data,
                info
        )
    }
}