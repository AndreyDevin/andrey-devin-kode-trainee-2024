package com.example.kode2024_test.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kode2024_test.domain.entity.Data
import com.example.kode2024_test.domain.entity.Intent
import com.example.kode2024_test.domain.entity.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    state: UiState,
    intent: (Intent) -> Unit
) {
    val enabled = !(state.data is Data.Error || state.data is Data.Loading)
    val tabState = remember { mutableIntStateOf(state.userChoice.department.ordinal) }

    val pullRefreshState = rememberPullToRefreshState()
    if (pullRefreshState.isRefreshing) {
        intent(Intent.Refresh)
        pullRefreshState.endRefresh()
    }

    Box(modifier = Modifier.nestedScroll(pullRefreshState.nestedScrollConnection)) {

        Column(modifier = Modifier) {

            Search(enabled = enabled, state = state.userChoice, intent = intent)

            DepartmentsTabRow(enabled = enabled, tabState = tabState, onTabClick = intent)

            state.data.also { data ->
                when (data) {

                    is Data.Loading ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 10.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.Blue)
                                    .padding(15.dp),
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                text = "Loading..."
                            )
                        }

                    is Data.EmployeesList -> EmployeesList(
                        sortingOption = state.userChoice.sortingOption,
                        list = data.list,
                        lazyListState = state.userChoice.lazyListState,
                        onItemClick = intent
                    )

                    is Data.EmptyList -> SearchResultNothing()

                    is Data.Error ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 10.dp)

                        ) {
                            Text(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.Red)
                                    .padding(15.dp),
                                text = data.errorText
                            )
                        }

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