package inf.datajpa.repository;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import inf.datajpa.dto.MemberDto;
import inf.datajpa.entity.Member;
import inf.datajpa.entity.Team;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@Transactional
class MemberRepositoryTest {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private MemberQueryRepository memberQueryRepository;

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

	@Test
	void findByNames() {
		// given
		Member member1 = new Member("AAA", 10);
		Member member2 = new Member("BBB", 20);
		memberRepository.saveAll(List.of(member1, member2));

		Set<String> names = new HashSet<>();
		names.add("AAA");
		names.add("BBB");

		// when
		// List<Member> members = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
		List<Member> members = memberRepository.findByNames(names);

		// then
		assertThat(members).hasSize(2)
			.extracting("username", "age")
			.containsExactlyInAnyOrder(
				tuple("AAA", 10),
				tuple("BBB", 20)
			);
	}

	@Test
	void page() {
		// given
		memberRepository.save(new Member("member1", 10));
		memberRepository.save(new Member("member2", 10));
		memberRepository.save(new Member("member3", 10));
		memberRepository.save(new Member("member4", 10));
		memberRepository.save(new Member("member5", 10));

		int age = 10;
		// Pageable 구현체
		PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

		//when
		Page<Member> page = memberRepository.findAsPageByAge(age, pageRequest);

		// then
		List<Member> content = page.getContent();
		long totalCount = page.getTotalElements();

		assertThat(content.size()).isEqualTo(3);
		assertThat(totalCount).isEqualTo(5);
		assertThat(page.getNumber()).isEqualTo(0);
		assertThat(page.getTotalPages()).isEqualTo(2); // 5 = 3 * '1' + '2'
		assertThat(page.isFirst()).isTrue();
		assertThat(page.hasNext()).isTrue();
	}

	@Test
	void slice() {
		// given
		memberRepository.save(new Member("member1", 10));
		memberRepository.save(new Member("member2", 10));
		memberRepository.save(new Member("member3", 10));
		memberRepository.save(new Member("member4", 10));
		memberRepository.save(new Member("member5", 10));

		int age = 10;
		// Pageable 구현체
		PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

		//when
		Slice<Member> slice = memberRepository.findAsSliceByAge(age, pageRequest);

		// then
		List<Member> content = slice.getContent();

		assertThat(content.size()).isEqualTo(3);
		assertThat(slice.getNumber()).isEqualTo(0);
		assertThat(slice.isFirst()).isTrue();
		assertThat(slice.hasNext()).isTrue();
	}

	@Test
	void list() {
		// given
		memberRepository.save(new Member("member1", 10));
		memberRepository.save(new Member("member2", 10));
		memberRepository.save(new Member("member3", 10));
		memberRepository.save(new Member("member4", 10));
		memberRepository.save(new Member("member5", 10));

		int age = 10;
		// Pageable 구현체
		PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

		// when
		List<Member> members = memberRepository.findAsListByAge(age, pageRequest);

		// then
		assertThat(members.size()).isEqualTo(3);
	}

/*
	@DisplayName("COUNT 쿼리없이 페치조인 불가능")
	@Test
	void count_query_separation() {
		// given
		memberRepository.save(new Member("member1", 10));
		memberRepository.save(new Member("member2", 10));
		memberRepository.save(new Member("member3", 10));
		memberRepository.save(new Member("member4", 10));
		memberRepository.save(new Member("member5", 10));

		int age = 10;
		PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

		// when
		Page<Member> page = memberRepository.findWithFetchJoinWithoutCountQueryByAge(age, pageRequest);

		// then
		assertThat(page.getContent().size()).isEqualTo(3);
	}
*/


	@DisplayName("직접 지정한 COUNT 쿼리없이 그냥 Paging 시 COUNT 쿼리에 JOIN 포함된다.")
	@Test
	void without_count_query() {
		// given
		memberRepository.save(new Member("member1", 10));
		memberRepository.save(new Member("member2", 10));
		memberRepository.save(new Member("member3", 10));
		memberRepository.save(new Member("member4", 10));
		memberRepository.save(new Member("member5", 10));

		int age = 10;
		PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

		// when
		Page<Member> page = memberRepository.findWithoutCountQueryByAge(age, pageRequest);
		Page<MemberDto> toMap = page.map(member -> new MemberDto(member.getId(), member.getUsername(), null));

		// then
		assertThat(page.getContent().size()).isEqualTo(3);
	}

	@DisplayName("COUNT 쿼리를 분리하기")
	@Test
	void with_count_query() {
		// given
		memberRepository.save(new Member("member1", 10));
		memberRepository.save(new Member("member2", 10));
		memberRepository.save(new Member("member3", 10));
		memberRepository.save(new Member("member4", 10));
		memberRepository.save(new Member("member5", 10));

		int age = 10;
		PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

		// when
		Page<Member> page = memberRepository.findWithCountQueryByAge(age, pageRequest);

		// then
		assertThat(page.getContent().size()).isEqualTo(3);
	}

	@Test
	void bulkUpdate() {
		// given
		memberRepository.save(new Member("member1", 10));
		memberRepository.save(new Member("member2", 19));
		memberRepository.save(new Member("member3", 20));
		memberRepository.save(new Member("member4", 21));
		memberRepository.save(new Member("member5", 40));

		// when
		int resultCount = memberRepository.bulkAgePlus(20);
		// em.clear();

		/**
		 * JPA 벌크성 쿼리 주의점 : DB와 영속성 컨텍스트가 다르다.
		 */
		Member member5 = memberRepository.findByUsername("member5").get();
		System.out.println("member5 = " + member5);

		// then
		assertThat(resultCount).isEqualTo(3);
	}

	@DisplayName("N + 1 문제")
	@Test
	void findTeamLazy() {
	    // given
		// Member (N) : (1) Team. Lazy Loading
		// member1 -> teamA
		// member2 -> teamB
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		teamRepository.save(teamA);
		teamRepository.save(teamB);

		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 10, teamB);
		memberRepository.save(member1);
		memberRepository.save(member2);

		em.flush();
		em.clear();

		// when
		List<Member> members = memberRepository.findAll();

		// then
		for (Member member : members) {
			System.out.println("member = " + member);
			System.out.println("member.getTeam().getClass() = " + member.getTeam().getClass());

			// 프록시 초기화, N + 1 문제 발생.
			System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
		}
	}

	@DisplayName("N + 1 문제 페치조인으로 해결")
	@Test
	void findTeamLazyWithFetch() {
		// given
		// Member (N) : (1) Team. Lazy Loading
		// member1 -> teamA
		// member2 -> teamB
		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		teamRepository.save(teamA);
		teamRepository.save(teamB);

		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 10, teamB);
		memberRepository.save(member1);
		memberRepository.save(member2);

		em.flush();
		em.clear();

		// when
		List<Member> members = memberRepository.findMemberFetchJoin();

		// then
		for (Member member : members) {
			System.out.println("member = " + member);
			System.out.println("member.getTeam().getClass() = " + member.getTeam().getClass());

			// 프록시 초기화, N + 1 문제 발생.
			System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
		}
	}

	@Test
	public void withoutQueryHint() throws Exception {
		//given
		memberRepository.save(new Member("member1", 10));
		em.flush();
		em.clear();

		//when
		Member findMember = memberRepository.findByUsername("member1").get();
		findMember.setUsername("member2");

		em.flush();// UPDATE 쿼리 실행
	}

	@Test
	public void queryHint() throws Exception {
		//given
		memberRepository.save(new Member("member1", 10));
		em.flush();
		em.clear();

		//when
		Member findMember = memberRepository.findReadOnlyByUsername("member1").get();
		findMember.setUsername("member2");

		em.flush();// UPDATE 쿼리 실행 X (더티 체킹 동작 X)
	}

	@Test
	public void lock() throws Exception {
		//given
		memberRepository.save(new Member("member1", 10));
		em.flush();
		em.clear();

		//when
		Member findMember = memberRepository.findLockByUsername("member1").get();
	}

	@Test
	void callCustomRepository() {
		List<Member> result = memberRepository.findMemberCustom();
	}

}