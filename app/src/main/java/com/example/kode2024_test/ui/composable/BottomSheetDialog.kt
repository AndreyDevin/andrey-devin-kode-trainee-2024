package com.example.kode2024_test.ui.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kode2024_test.ui.models.Intent
import com.example.kode2024_test.domain.entity.SortingOption
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDialog(
    selectedSortingOption: SortingOption,
    intent: (Intent.SortingSelect) -> Unit
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()

    Icon(
        modifier = Modifier
            .clickable {
                scope.launch {
                    sheetState.show()
                }
                       },
        imageVector = Icons.AutoMirrored.Filled.List,
        contentDescription = "",
        tint =
        if (selectedSortingOption == SortingOption.ByBrithDay) Color.Magenta
        else Color.Gray,
    )

    if (sheetState.isVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            modifier = Modifier.height(240.dp),
            onDismissRequest = {
                scope.launch {
                    sheetState.hide()
                }
            },
        ) {
            Column {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .padding(bottom = 8.dp),
                    text = "Сортировка",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                
                Row ( modifier = Modifier.padding(top = 10.dp) ) {
                    RadioButton(
                        selected = selectedSortingOption == SortingOption.ByAlphabet,
                        onClick = {
                            intent(Intent.SortingSelect(SortingOption.ByAlphabet))
                            scope.launch {
                                sheetState.hide()
                            }
                        }
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clickable {
                                intent(Intent.SortingSelect(SortingOption.ByAlphabet))
                                scope.launch {
                                    sheetState.hide()
                                }
                            }
                        ,
                        fontWeight = FontWeight.Bold,
                        text = "По алфавиту"
                        )
                }
                
                Row ( modifier = Modifier.padding(top = 10.dp) ) {
                    RadioButton(
                        selected = selectedSortingOption == SortingOption.ByBrithDay,
                        onClick = {
                            intent(Intent.SortingSelect(SortingOption.ByBrithDay))
                            scope.launch {
                                sheetState.hide()
                            }
                        }
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .clickable {
                                intent(Intent.SortingSelect(SortingOption.ByBrithDay))
                                scope.launch {
                                    sheetState.hide()
                                }
                            }
                        ,
                        fontWeight = FontWeight.Bold,
                        text = "По дню рождения"
                    )
                }
            }
        }
    }
}