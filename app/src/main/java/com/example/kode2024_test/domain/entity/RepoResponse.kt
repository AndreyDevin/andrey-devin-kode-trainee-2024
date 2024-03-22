package com.example.kode2024_test.domain.entity

sealed class RepoResponse {
    class SuccessResponse(val data: List<Employee>): RepoResponse()
    class ErrorResponse(val message: String): RepoResponse()
}