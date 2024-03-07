package com.example.kode2024_test.data.dto

import com.example.kode2024_test.entity.EmployeeEntity

data class Employee(
    override val id: String,
    override val avatarUrl: String,
    override val firstName: String,
    override val lastName: String,
    override val userTag: String,
    override val department: String,
    override val position: String,
    override val birthday: String,
    override val phone: String
): EmployeeEntity
