package com.example.kode2024_test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kode2024_test.data.UseCase
import com.example.kode2024_test.data.dto.Employee
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.ZonedDateTime

class MainViewModel(
    private val useCase: UseCase
): ViewModel() {
    private val _state: MutableStateFlow<List<Employee>?> = MutableStateFlow(null)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getData()

        }
    }

    private suspend fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = useCase.getAll()
            selectSortingOption(SortingOption.ByBrithDay)
            //_state.value = useCase.getDepartment("android")
            //_state.value = useCase.find("hAnNa")
        }
    }

    fun selectSortingOption(options: SortingOption) {

        when (options) {
            SortingOption.ByAlphabet -> _state.value =
                _state.value?.sortedBy { it.firstName }

            SortingOption.ByBrithDay -> _state.value =
                _state.value?.filter {
                    it.birthday.dayOfYear >= ZonedDateTime.now().dayOfYear
                }?.sortedBy {
                    it.birthday.dayOfYear
                }
        }
    }
}

enum class SortingOption {
    ByAlphabet,
    ByBrithDay
}

