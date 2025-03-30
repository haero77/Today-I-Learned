package com.example.ktsecurityexample.member.controller

import com.example.ktsecurityexample.member.dto.MemberDtoRequest
import com.example.ktsecurityexample.member.entity.Member
import com.example.ktsecurityexample.member.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class MemberController(
    private val memberService: MemberService,
) {
    /**
     * 회원가입
     */
    @PostMapping("/api/members/sign-up")
    fun signUp(
        @RequestBody memberDtoRequest: MemberDtoRequest,
    ): ResponseEntity<Member> {
        val member = memberService.signUp(memberDtoRequest)
        return ResponseEntity.ok(member)
    }
}
