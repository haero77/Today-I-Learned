package com.example.ktsecurityexample.member.service

import com.example.ktsecurityexample.member.dto.MemberDtoRequest
import com.example.ktsecurityexample.member.entity.Member
import com.example.ktsecurityexample.member.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {

    /**
     * 회원가입
     */
    fun signUp(memberDtoRequest: MemberDtoRequest): Member {
        memberRepository.findByLoginId(memberDtoRequest.loginId)?.let {
            throw IllegalArgumentException("이미 존재하는 아이디입니다.")
        }

        val member = memberDtoRequest.toNewMember()

        return memberRepository.save(member)
    }
}
