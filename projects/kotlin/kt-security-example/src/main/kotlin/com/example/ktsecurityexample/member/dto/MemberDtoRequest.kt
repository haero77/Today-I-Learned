package com.example.ktsecurityexample.member.dto

import com.example.ktsecurityexample.common.status.Gender
import com.example.ktsecurityexample.member.entity.Member
import java.time.LocalDate

data class MemberDtoRequest(
    val id: Long?,
    val loginId: String,
    val password: String,
    val name: String,
    val birthDate: LocalDate,
    val gender: Gender,
    val email: String,
) {
    fun toNewMember(): Member {
        return Member(
            id = null,
            loginId = loginId,
            password = password,
            name = name,
            birthDate = birthDate,
            gender = gender,
            email = email,
        )
    }
}
