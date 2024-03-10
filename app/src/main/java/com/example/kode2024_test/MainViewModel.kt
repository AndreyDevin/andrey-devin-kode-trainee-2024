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
}

enum class Department(val title: String) {
    ANDROID("android"),
    IOS("ios"),
    DESIGN("design"),
    MANAGEMENT("management"),
    QA("qa"),
    BACK_OFFICE("back_office"),
    FRONTEND("frontend"),
    HR("hr"),
    PR("pr"),
    BACKEND("backend"),
    SUPPORT("support"),
    ANALYTICS("analytics")
}
/*val DEPARTMENT_LIST = listOf(
    "android",
    "ios",
    "design",
    "management",
    "qa",
    "back_office",
    "frontend",
    "hr",
    "pr",
    "backend",
    "support",
    "analytics"
)*/
