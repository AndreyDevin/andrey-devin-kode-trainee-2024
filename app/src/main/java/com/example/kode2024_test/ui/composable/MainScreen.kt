package com.example.kode2024_test.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.kode2024_test.ui.entity.Data
import com.example.kode2024_test.ui.entity.Intent
import com.example.kode2024_test.ui.entity.UiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: UiState,
    intent: (Intent) -> Unit
) {
    val tabState = remember { mutableIntStateOf(state.userChoice.department.ordinal) }

    val pullRefreshState = rememberPullToRefreshState()
    if (pullRefreshState.isRefreshing) {
        intent(Intent.Refresh)
        pullRefreshState.endRefresh()
    }

    Box(modifier = Modifier.nestedScroll(pullRefreshState.nestedScrollConnection)) {

        Column {

            Search(state = state.userChoice.sortingOption, intent = intent)

            DepartmentsTabRow(tabState = tabState, onTabClick = intent)

            state.data.also { data ->
                when (data) {

                    is Data.Loading -> Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) { CircularProgressIndicator() }

                    is Data.EmployeesList -> EmployeesList(
                        sortingOption = state.userChoice.sortingOption,
                        list = data.list,
                        lazyListState = state.userChoice.lazyListState,
                        onItemClick = intent
                    )

                    is Data.EmptyList -> SearchResultNothing()

                    else -> {}
                }
            }
        }

        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullRefreshState,
        )
    }
}