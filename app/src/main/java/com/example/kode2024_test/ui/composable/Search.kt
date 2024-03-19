package com.example.kode2024_test.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kode2024_test.ui.entity.Intent
import com.example.kode2024_test.ui.entity.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    state: UiState,
    intent: (Intent) -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp),
        query = text,
        onQueryChange = {
            text = it
            intent(Intent.Search(it))
        },
        onSearch = {},
        active = false,
        onActiveChange = {},
        placeholder = { Text(text = "Введи имя, тег, почту...") },
        leadingIcon = {
            Icon(
                modifier = Modifier.clickable { intent(Intent.Search(text)) },
                imageVector = Icons.Default.Search,
                contentDescription = ""
            )
                      },
        trailingIcon = {
            Row {
                Icon(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .clickable {
                            text = ""
                            intent(Intent.Search(text))
                                   },
                    imageVector = Icons.Default.Clear,
                    contentDescription = "clear text"
                )
                BottomSheetDialog(
                    selectedSortingOption = state.userChoice.sortingOption,
                    intent = intent
                )
            }
        },
    ) {}
}