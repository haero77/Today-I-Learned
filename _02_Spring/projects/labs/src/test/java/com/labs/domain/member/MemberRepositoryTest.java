package com.labs.domain.member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberRepositoryTest {

	@Autowired
	private MemberRepository memberRepository;

	@DisplayName("username으로 회원을 조회할 수 있다.")
	@Test
	void findMemberByUsername() {
	    // given
		String username = "username";
		Member member = new Member(username);
		memberRepository.save(member);

		// when
		Member findMember = memberRepository.findMemberByUsername(username).get();

		// then
		assertThat(findMember.getUsername()).isEqualTo(username);
	}

}