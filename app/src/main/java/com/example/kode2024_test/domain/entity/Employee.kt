package com.example.kode2024_test.domain.entity

import java.time.ZonedDateTime

data class Employee(
    val id: String,
    val avatarUrl: String,
    val firstName: String,
    val lastName: String,
    val userTag: String,
    val department: String,
    val position: String,
    val birthday: ZonedDateTime,
    val phone: String
)

