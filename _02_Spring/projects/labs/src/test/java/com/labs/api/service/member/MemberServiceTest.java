package com.labs.api.service.member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.labs.domain.member.Member;
import com.labs.domain.member.MemberRepository;

@SpringBootTest
class MemberServiceTest {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MemberService memberService;

	@AfterEach
	void tearDown() {
		memberRepository.deleteAll();
	}

	@DisplayName("회원의 username을 변경할 수 있다.")
	@Test
	void changeUsername() {
		// given
		Member member = new Member("oldUsername");
		Member savedMember = memberRepository.save(member);

		Long savedMemberId = savedMember.getId();
		String newUsername = "newUsername";

		// when
		memberService.changeUsername(savedMemberId, newUsername);

		// then
		Member findMember = memberRepository.findById(savedMemberId).get();
		assertThat(findMember.getUsername()).isEqualTo(newUsername);
	}

	@DisplayName("username으로 회원을 생성할 수 있다.")
	@Test
	void createMember() {
		// given
		String username = "username";

		// when
		Long createdMemberId = memberService.createMember(username);

		// then
		Member findMember = memberRepository.findById(createdMemberId).get();
		assertThat(findMember.getUsername()).isEqualTo(username);
	}

}