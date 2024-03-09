package com.example.kode2024_test.di

import com.example.kode2024_test.MainViewModel
import com.example.kode2024_test.api.KodeOpenApi
import com.example.kode2024_test.data.Repo
import com.example.kode2024_test.data.UseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {

    factory { KodeOpenApi.create() }
    factory { Repo(get()) }
    factory { UseCase(get()) }
    viewModel {
        MainViewModel(get())
    }
}