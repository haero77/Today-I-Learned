package com.labs.api.service.member;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.labs.api.service.member.request.MemberTeamChangeRequest;
import com.labs.api.service.member.respoonse.MemberTeamChangeResponse;
import com.labs.domain.member.Member;
import com.labs.domain.member.MemberRepository;
import com.labs.domain.team.Team;
import com.labs.domain.team.TeamRepository;

@SpringBootTest
// @Transactional
// @Rollback(value = false)
class MemberCommandServiceTest {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private MemberCommandService memberCommandService;

	@AfterEach
	void tearDown() {
		memberRepository.deleteAllInBatch();
		teamRepository.deleteAllInBatch();
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
		memberCommandService.changeUsername(savedMemberId, newUsername);

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
		Long createdMemberId = memberCommandService.createMember(username);

		// then
		Member findMember = memberRepository.findById(createdMemberId).get();
		assertThat(findMember.getUsername()).isEqualTo(username);
	}

	@DisplayName("회원 ID와 팀 ID를 통해 회원의 팀을 변경할 수 있다.")
	@Test
	void changeTeam() {
	    // given
		Team oldTeam = new Team("oldTeam");
		teamRepository.save(oldTeam);

		Member member = new Member("member1", oldTeam);
		memberRepository.save(member);

		Team newTeam = new Team("newTeam");
		teamRepository.save(newTeam);

		MemberTeamChangeRequest request = new MemberTeamChangeRequest(member.getId(), newTeam.getId());

		// when
		MemberTeamChangeResponse response = memberCommandService.changeTeam(request);

		// then
		Member findMember = memberRepository.findById(member.getId()).get();
		assertThat(findMember.getTeam().getId()).isEqualTo(newTeam.getId());

		assertThat(response)
			.extracting("memberId", "teamId")
			.contains(
				member.getId(), newTeam.getId()
			);
	}

}