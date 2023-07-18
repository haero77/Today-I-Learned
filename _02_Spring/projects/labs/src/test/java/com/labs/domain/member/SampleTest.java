package com.labs.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.labs.domain.team.Team;
import com.labs.domain.team.TeamRepository;

@SpringBootTest
class SampleTest {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private MemberRepository memberRepository;

	@AfterEach
	void tearDown() {
		memberRepository.deleteAllInBatch();
		teamRepository.deleteAllInBatch();
	}

	@Test
	void sample() {
	    // given
		Team team1 = new Team("team1");
		teamRepository.save(team1);

		Member member1 = new Member("member1", team1);
		memberRepository.save(member1);

	    // when



	    // then
	}

}