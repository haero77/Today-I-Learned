package com.labs.api.service.member;

import org.springframework.stereotype.Service;

import com.labs.domain.member.Member;
import com.labs.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;

	public Long createMember(String username) {
		Member newMember = new Member(username);
		Member savedMember = memberRepository.save(newMember);
		return savedMember.getId();
	}

	public void changeUsername(Long id, String newUsername) {
		Member findMember = memberRepository.findById(id).orElseThrow();
		findMember.changeUsername(newUsername);
	}

}
