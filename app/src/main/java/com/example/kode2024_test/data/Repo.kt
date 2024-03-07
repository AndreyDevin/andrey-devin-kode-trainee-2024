package com.example.kode2024_test.data

import com.example.kode2024_test.api.KodeOpenApi
import com.example.kode2024_test.data.dto.Response

class Repo(
    private val api: KodeOpenApi
) {
    suspend fun getResponse(): Response = api.getResponse()
}