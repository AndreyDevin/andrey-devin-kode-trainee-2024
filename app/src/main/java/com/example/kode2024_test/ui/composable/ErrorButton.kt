package com.example.kode2024_test.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kode2024_test.domain.entity.Data
import com.example.kode2024_test.domain.entity.Intent
import com.example.kode2024_test.domain.entity.UiState

@Composable
fun ErrorButton(
    state: UiState,
    intent: (Intent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp, end = 10.dp)
            .wrapContentSize(Alignment.BottomEnd)
    ) {
        Row {
            Checkbox(
                enabled = !(state.data is Data.Error || state.data is Data.Loading),
                checked = state.userChoice.errorGenerate,
                onCheckedChange = { intent(Intent.ErrorGenerate(!state.userChoice.errorGenerate)) }
            )
            Text(text = "with\nerror")
        }
    }
}