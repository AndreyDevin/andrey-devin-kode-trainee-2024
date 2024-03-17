package com.example.kode2024_test.data

import com.example.kode2024_test.data.dto.Employee
import com.example.kode2024_test.ui.entity.Department
import com.example.kode2024_test.ui.entity.SortingOption
import java.time.ZonedDateTime

class UseCase(
    private val repo: Repo
) {
    suspend fun updateData(
        department: Department,
        searchField: String,
        sortingOption: SortingOption
    ): List<Employee> {
        var data = repo.getResponse()

        if (department != Department.All) data = data.filter { it.department == department.title }

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

                data = inThisYear.plus(createSpliterator()).plus(inNextYear)
            }
        }
        return data
    }

    suspend fun getDetails(id: String) = repo.getResponse().filter { it.id == id }

    private fun createSpliterator() = Employee(
        id = Employee.FLAG_TO_SHARE_WHEN_BIRTHDAY_IS_NEXT_YEAR,
        "",
        "",
        "",
        "",
        "",
        "",
        ZonedDateTime.now().plusYears(1),
        ""
    )
}