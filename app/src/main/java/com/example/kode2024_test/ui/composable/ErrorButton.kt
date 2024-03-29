package com.example.kode2024_test.ui.composable

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
    var checked by rememberSaveable { mutableStateOf(state.options.errorGenerate) }
    Log.d("MyTag", checked.toString())
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 10.dp, end = 10.dp)
            .wrapContentSize(Alignment.BottomEnd)
    ) {
        Row {
            Checkbox(
                enabled = state.data !is Data.Loading,
                checked = checked,
                onCheckedChange = {
                    checked = !checked
                    intent(Intent.ErrorGenerate(checked))
                }
            )
            Text(text = "with\nerror")
        }
    }
}