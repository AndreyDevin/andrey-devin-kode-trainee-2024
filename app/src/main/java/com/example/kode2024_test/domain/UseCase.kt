package com.example.kode2024_test.domain

import com.example.kode2024_test.domain.entity.Employee
import com.example.kode2024_test.domain.entity.Department
import com.example.kode2024_test.domain.entity.SortingOption
import java.time.ZonedDateTime

class UseCase(
    private val repo: Repo
) {
    suspend fun updateData(
        department: Department,
        searchField: String,
        sortingOption: SortingOption
    ): List<Employee> {
        var data = repo.getData()

        if (department != Department.All) data =
            data.filter { it.department == department.name.lowercase() }

        if (searchField != "") data = data.filter {
            it.firstName.contains(Regex(searchField, RegexOption.IGNORE_CASE)) ||
                    it.lastName.contains(Regex(searchField, RegexOption.IGNORE_CASE)) ||
                    it.userTag.contains(Regex(searchField, RegexOption.IGNORE_CASE))
        }

        when (sortingOption) {
            SortingOption.ByAlphabet -> data = data.sortedBy { it.firstName }

            SortingOption.ByBrithDay -> {
                val inThisYear = data.filter {
                    it.birthday.dayOfYear >= ZonedDateTime.now().dayOfYear
                }.sortedBy {
                    it.birthday.dayOfYear
                }

                val inNextYear = data.filter {
                    it.birthday.dayOfYear < ZonedDateTime.now().dayOfYear
                }.sortedBy {
                    it.birthday.dayOfYear
                }

                data = inThisYear.plus(inNextYear)
            }
        }
        return data
    }

    suspend fun getDetails(id: String) = repo.getData().filter { it.id == id }
}