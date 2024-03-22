package com.example.kode2024_test.ui.composable

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.kode2024_test.domain.entity.Department
import com.example.kode2024_test.domain.entity.Intent

@Composable
fun DepartmentsTabRow(
    enabled: Boolean,
    tabState: MutableIntState,
    onTabClick: (Intent.DepartmentSelect) -> Unit
    ) {
    ScrollableTabRow(
        selectedTabIndex = tabState.intValue,
        edgePadding = (-10).dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[tabState.intValue])
            )
        }
    ) {
        Department.entries.forEachIndexed { index, department ->
            Tab(
                modifier = Modifier.height(40.dp),
                enabled = enabled,
                selected = index == tabState.intValue,
                onClick = {
                    tabState.intValue = index
                    onTabClick(Intent.DepartmentSelect(department))
                }
            ) {
                Text(
                    text = department.title,
                    color = if (index == tabState.intValue) textColor() else Color.Gray
                )
            }
        }
    }
}

@Composable
fun textColor(): Color =
    if (isSystemInDarkTheme()) Color.White
    else Color.Black