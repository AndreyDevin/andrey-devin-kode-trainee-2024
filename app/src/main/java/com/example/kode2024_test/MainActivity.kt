package com.example.kode2024_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.kode2024_test.ui.entity.Department
import com.example.kode2024_test.ui.entity.Intent
import com.example.kode2024_test.ui.entity.SortingOption
import com.example.kode2024_test.ui.entity.UiState
import com.example.kode2024_test.ui.theme.Kode2024_testTheme
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainViewModel by viewModel<MainViewModel>()

        viewModel.executeIntent(Intent.DepartmentSelect(Department.ANDROID))
        viewModel.executeIntent(Intent.SortingSelect(SortingOption.ByBrithDay))
        viewModel.executeIntent(Intent.Search("ari"))
        viewModel.executeIntent(Intent.Refresh)

        lifecycleScope.launch {
            viewModel.state.collect { response ->

                setContent {
                    Kode2024_testTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            val info =
                                if (response is UiState.EmployeesList) response.list.toString()
                                else response.toString()
                            Greeting( info )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Kode2024_testTheme {
        Greeting("Android")
    }
}