package com.example.kode2024_test.domain

import com.example.kode2024_test.api.KodeOpenApi
import com.example.kode2024_test.api.models.BodyItem
import com.example.kode2024_test.domain.entity.Employee
import java.time.LocalDate
import java.time.ZoneId

class Repo(
    private val api: KodeOpenApi
) {

    suspend fun getData(withError: Boolean = false): List<Employee> = api
        .getResponse(if (withError) ERROR_HEADER else SUCCESS_HEADER)
        .body()?.items?.map { apiModelToEmployee(it) } ?: emptyList()

    private fun apiModelToEmployee(item: BodyItem): Employee {
        return Employee(
            id = item.id,
            avatarUrl = item.avatarUrl,
            firstName = item.firstName,
            lastName = item.lastName,
            userTag = item.userTag,
            department = item.department,
            position = item.position,
            birthday = LocalDate.parse(item.birthday).atStartOfDay(ZoneId.systemDefault()),
            phone = item.phone
        )
    }

    private companion object {
        const val SUCCESS_HEADER = "Prefer: code=200, example=success"
        const val ERROR_HEADER = "Prefer: code=500, example=error-500"
    }
}