package com.example.kode2024_test.ui.models

import com.example.kode2024_test.domain.entity.Data
import com.example.kode2024_test.domain.entity.DataState

data class State (
    val options: Options,
    val data: Data,
    val dataState: DataState?
)

