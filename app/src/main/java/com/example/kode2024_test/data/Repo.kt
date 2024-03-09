package com.example.kode2024_test.data

import com.example.kode2024_test.api.KodeOpenApi
import com.example.kode2024_test.api.models.BodyItem
import com.example.kode2024_test.data.dto.Employee
import java.time.LocalDate
import java.time.ZoneId

class Repo(
    private val api: KodeOpenApi
) {
    suspend fun getResponse(): List<Employee> = api.getResponse().items.map { bodyItemToEmployee(it) }

    private fun bodyItemToEmployee(item: BodyItem): Employee {
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
}