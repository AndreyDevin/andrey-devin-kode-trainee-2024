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
        set(value) {
            field = value
            updateData()
        }

    private var searchField = ""
        set(value) {
            field = value
            updateData()
        }

    private var sortingOption = SortingOption.ByAlphabet
        set(value) {
            field = value
            updateData()
        }

    private val _state: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            executeIntent(Intent.DepartmentSelect(Department.All))
        }
    }

    fun executeIntent(intent: Intent) {
        when(intent) {
            is Intent.DepartmentSelect -> department = intent.department
            is Intent.Search -> searchField = intent.searchField
            is Intent.SortingSelect -> sortingOption = intent.option
            is Intent.Details -> TODO()
            Intent.Refresh -> updateData()
        }
    }

    private fun updateData() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = UiState.Loading
            _state.value = UiState.EmployeesList(
                list = useCase.updateData(department, searchField, sortingOption),
                department = department,
                sortingOption = sortingOption
            )
        }
    }
}
/*
class MainViewModel(
    private val useCase: UseCase
): ViewModel() {

    private var dataOptions: Intent.DataOptions = Intent.DataOptions.TotalList
        set(value) {
            field = value
            executeDataOptions()
        }

    private var sortingOption = Intent.SortingOption.ByAlphabet
        set(value) {
            field = value
            executeSortingOption()
        }

    private val _state: MutableStateFlow<List<Employee>?> = MutableStateFlow(null)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            executeDataOptions()
        }
    }

    fun executeIntent(intent: Intent) {
        when(intent) {
            is Intent.DataOptions.TotalList -> dataOptions = intent
            is Intent.DataOptions.SingleDepartment -> dataOptions = intent
            is Intent.DataOptions.Search -> dataOptions = intent
            is Intent.DataOptions.Details -> dataOptions = intent
            is Intent.SortingOption -> sortingOption = intent
            is Intent.Refresh -> executeDataOptions()
        }
    }

    private fun executeDataOptions() {
        viewModelScope.launch(Dispatchers.IO) {
            when(dataOptions) {
                is Intent.DataOptions.TotalList -> _state.value = useCase.getAll()
                is Intent.DataOptions.SingleDepartment -> _state.value =
                    useCase.getDepartment((dataOptions as Intent.DataOptions.SingleDepartment).department.title)
                is Intent.DataOptions.Search -> _state.value =
                    useCase.find((dataOptions as Intent.DataOptions.Search).searchField)
                is Intent.DataOptions.Details -> {}
            }
            executeSortingOption()
        }
    }


    private fun executeSortingOption() {
        if (!_state.value.isNullOrEmpty()) {

            when (sortingOption) {
                Intent.SortingOption.ByAlphabet -> _state.value =
                    _state.value?.sortedBy { it.firstName }

                Intent.SortingOption.ByBrithDay -> {
                    val after = _state.value!!.filter {
                        it.birthday.dayOfYear >= ZonedDateTime.now().dayOfYear
                    }.sortedBy {
                        it.birthday.dayOfYear
                    }

                    val before = _state.value!!.filter {
                        it.birthday.dayOfYear < ZonedDateTime.now().dayOfYear
                    }.sortedBy {
                        it.birthday.dayOfYear
                    }

                    _state.value = after.plus(before)
                    //state.value?.forEach { Log.d("MyTag", it.birthday.toString()) }
                }
            }
        }
    }
}
*/