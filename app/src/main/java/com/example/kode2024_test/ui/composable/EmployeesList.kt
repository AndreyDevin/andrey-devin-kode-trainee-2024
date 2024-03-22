package com.example.kode2024_test.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.kode2024_test.domain.entity.Employee
import com.example.kode2024_test.domain.entity.Intent
import com.example.kode2024_test.domain.entity.SortingOption
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun EmployeesList(
    sortingOption: SortingOption,
    list: List<Employee>,
    lazyListState: LazyListState,
    onItemClick: (Intent.Details) -> Unit
) {
    when (sortingOption) {

        SortingOption.ByAlphabet -> {
            LazyColumn(
                modifier = Modifier.padding(start = 14.dp),
                state = lazyListState
            ) {
                items(count = list.size) { index ->
                    EmployeesListItem(
                        employee = list[index],
                        clickListener = onItemClick,
                        birthday = ""
                    )
                }
            }
        }


        SortingOption.ByBrithDay -> {
            val dividerIndex =
                list.indexOfFirst { it.birthday.dayOfYear < ZonedDateTime.now().dayOfYear }

            LazyColumn(
                modifier = Modifier.padding(start = 14.dp, end = 14.dp),
                state = lazyListState

            ) {
                items(count = list.size) { index ->
                    if (dividerIndex == index) NextYearBirthdayDivider()

                    EmployeesListItem(
                        employee = list[index],
                        clickListener = onItemClick,
                        birthday = birthDayToString(list[index].birthday)
                    )
                }
            }
        }
    }
}

fun birthDayToString(zonedDateTime: ZonedDateTime): String =
    zonedDateTime.format(DateTimeFormatter.ofPattern("dd MMM", Locale.getDefault()))