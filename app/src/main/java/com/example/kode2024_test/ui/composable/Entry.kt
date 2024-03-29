package com.example.kode2024_test.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.example.kode2024_test.MainViewModel
import com.example.kode2024_test.domain.entity.Data
import com.example.kode2024_test.domain.entity.Intent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Entry(
    viewModel: MainViewModel
) {
    val state by viewModel.state.collectAsState()

    val pullRefreshState = rememberPullToRefreshState()
    if (pullRefreshState.isRefreshing) {
        viewModel.executeIntent(Intent.Refresh)
        pullRefreshState.endRefresh()
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .nestedScroll(pullRefreshState.nestedScrollConnection)
    ) {

        if (state.data is Data.EmployeeDetails) Details(
            employee = (state.data as Data.EmployeeDetails).employee,
            onBackPressed = { viewModel.executeIntent(Intent.OnBackPressed) }
        )
        else {
            MainScreen(
                state = state,
                intent = viewModel::executeIntent
            )
        }

        state.info?.let { Info(info = it) }

        ErrorButton(
            state = state,
            intent = viewModel::executeIntent
        )

        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullRefreshState,
        )
    }
}