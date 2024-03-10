package com.example.kode2024_test.data

class UseCase(
    private val repo: Repo
) {
    suspend fun getAll() = repo.getResponse()

    suspend fun getDepartment(department: String) = getAll().filter { it.department == department }

    suspend fun find(query: String) = getAll().filter {
        it.firstName.contains(Regex(query, RegexOption.IGNORE_CASE)) ||
        it.lastName.contains(Regex(query, RegexOption.IGNORE_CASE)) ||
        it.userTag.contains(Regex(query, RegexOption.IGNORE_CASE))
    }

    //suspend fun getDetails(id: String) = getAll().filter { it.id == id }
}