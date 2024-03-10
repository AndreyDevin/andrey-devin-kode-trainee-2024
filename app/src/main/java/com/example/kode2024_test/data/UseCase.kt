package com.example.kode2024_test.data

import com.example.kode2024_test.data.dto.Employee
import com.example.kode2024_test.ui.entity.Department
import com.example.kode2024_test.ui.entity.Intent
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
                val after = data.filter {
                    it.birthday.dayOfYear >= ZonedDateTime.now().dayOfYear
                }.sortedBy {
                    it.birthday.dayOfYear
                }

                val before = data.filter {
                    it.birthday.dayOfYear < ZonedDateTime.now().dayOfYear
                }.sortedBy {
                    it.birthday.dayOfYear
                }

                data = after.plus(before)
                //state.value?.forEach { Log.d("MyTag", it.birthday.toString()) }
            }
        }
        return data
    }
}
    /*suspend fun getAll() = repo.getResponse()

    suspend fun getDepartment(department: String) = getAll().filter { it.department == department }

    suspend fun find(query: String) = getAll().filter {
        it.firstName.contains(Regex(query, RegexOption.IGNORE_CASE)) ||
        it.lastName.contains(Regex(query, RegexOption.IGNORE_CASE)) ||
        it.userTag.contains(Regex(query, RegexOption.IGNORE_CASE))
    }*/

    //suspend fun getDetails(id: String) = getAll().filter { it.id == id }