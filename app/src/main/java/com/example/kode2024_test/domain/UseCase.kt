package com.example.kode2024_test.domain

import com.example.kode2024_test.domain.entity.Data
import com.example.kode2024_test.domain.entity.Department
import com.example.kode2024_test.domain.entity.RepoResponse
import com.example.kode2024_test.domain.entity.SortingOption
import java.time.ZonedDateTime

class UseCase(
    private val repo: Repo
) {
    suspend fun updateData(id: String, withError: Boolean): Data {
        repo.getData(withError).also { response ->
            return when(response) {

                is RepoResponse.SuccessResponse -> {
                    try {
                        Data.EmployeeDetails(response.data.first { it.id == id })
                    } catch (exception: NoSuchElementException) {
                        Data.Error("No such details")
                    }
                }

                is RepoResponse.ErrorResponse -> Data.Error(response.message)
            }
        }
    }

    suspend fun updateData(
        department: Department,
        searchField: String,
        sortingOption: SortingOption,
        withError: Boolean
    ): Data {

        repo.getData(withError).also { response ->

            when (response) {
                is RepoResponse.SuccessResponse -> {

                    var data = response.data

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
                    return if (data.isNotEmpty()) Data.EmployeesList(data)
                    else Data.EmptyList
                }

                is RepoResponse.ErrorResponse -> return Data.Error(response.message)
            }
        }
    }
}