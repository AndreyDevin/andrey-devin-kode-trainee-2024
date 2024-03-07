package com.example.kode2024_test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kode2024_test.data.Repo
import com.example.kode2024_test.data.dto.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repo: Repo
): ViewModel() {
    private val _state: MutableStateFlow<Response?> = MutableStateFlow(null)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getData()
        }
    }

    private suspend fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = repo.getResponse()
        }
    }
}