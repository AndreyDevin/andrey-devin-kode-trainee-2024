package com.example.kode2024_test.ui.models

import androidx.compose.foundation.lazy.LazyListState
import com.example.kode2024_test.domain.entity.Department
import com.example.kode2024_test.domain.entity.SortingOption

data class Options(
    val department: Department,
    val searchField: String,
    val sortingOption: SortingOption,
    val lazyListState: LazyListState,
    val errorGenerate: Boolean
)