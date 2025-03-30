package com.example.ktsecurityexample.member.repository

import com.example.ktsecurityexample.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByLoginId(loginId: String): Member?
}
