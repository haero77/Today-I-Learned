package inf.datajpa.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import inf.datajpa.dto.MemberDto;
import inf.datajpa.entity.Member;
import inf.datajpa.entity.Team;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Test
	void save() {
		Member member = new Member("memberB");

		Member savedMember = memberRepository.save(member);

		Member findMember = memberRepository.findById(savedMember.getId()).get();
		assertThat(findMember.getId()).isEqualTo(member.getId());
		assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
		assertThat(findMember).isEqualTo(member);
	}

	@Test
	void basicCRUD() {
		Member member1 = new Member("member1");
		Member member2 = new Member("member2");
		memberRepository.save(member1);
		memberRepository.save(member2);

		// 단건 조회 검증
		Member findMember1 = memberRepository.findById(member1.getId()).get();
		Member findMember2 = memberRepository.findById(member2.getId()).get();
		assertThat(findMember1).isEqualTo(member1);
		assertThat(findMember2).isEqualTo(member2);

		// 리스트 조회 검증
		List<Member> all = memberRepository.findAll();
		assertThat(all.size()).isEqualTo(2);

		// 카운트 검증
		long count = memberRepository.count();
		assertThat(count).isEqualTo(2);

		// 삭제 검증
		memberRepository.delete(member1);
		memberRepository.delete(member2);

		long deletedCount = memberRepository.count();
		assertThat(deletedCount).isEqualTo(0);
	}

	@Test
	void findByUsernameAndAgeGreaterThan() {
		// given
		Member member1 = new Member("username", 10);
		Member member2 = new Member("username", 20);
		memberRepository.saveAll(List.of(member1, member2));

		// when
		List<Member> members = memberRepository.findByUsernameAndAgeGreaterThan("username", 15);

		// then
		assertThat(members).hasSize(1)
			.extracting("username", "age")
			.containsExactlyInAnyOrder(
				tuple("username", 20)
			);
	}

	@Test
	void findTop3HelloBy() {
		List<Member> top3HelloBy = memberRepository.findTop3HelloBy();
	}

	@Test
	void findUser() {
		// given
		Member member1 = new Member("AAA", 10);
		Member member2 = new Member("BBB", 20);
		memberRepository.saveAll(List.of(member1, member2));

		// when
		Member findMember = memberRepository.findUser("BBB", 20).get();

		// then
		assertThat(findMember.getUsername()).isEqualTo("BBB");
		assertThat(findMember.getAge()).isEqualTo(20);
	}

	@Test
	void findUsernameList() {
		// given
		Member member1 = new Member("AAA", 10);
		Member member2 = new Member("BBB", 20);
		memberRepository.saveAll(List.of(member1, member2));

		// when
		List<String> usernameList = memberRepository.findUsernameList();

		// then
		assertThat(usernameList).hasSize(2)
			.containsExactlyInAnyOrder(
				"AAA",
				"BBB"
			);
	}

	@Test
	void findMemberDto() {
		// given
		Team team = new Team("teamA");
		teamRepository.save(team);

		Member member = new Member("AAA", 10);
		member.changeTeam(team);
		memberRepository.save(member);

		// when
		List<MemberDto> memberDtos = memberRepository.findMemberDto();

		// then
		assertThat(memberDtos).hasSize(1)
			.extracting("username", "teamName")
			.containsExactly(
				tuple("AAA", "teamA")
			);
	}

}