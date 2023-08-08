package inf.querydsl.repository;

import inf.querydsl.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberJpaRepositoryTest {

	@Autowired
	EntityManager em;

	@Autowired
	MemberJpaRepository memberJpaRepository;

	@Test
	void basicTest() {
		Member member = new Member("member1", 10);
		memberJpaRepository.save(member);

		Member findMember = memberJpaRepository.findById(member.getId()).get();
		assertThat(findMember).isEqualTo(member);

		List<Member> result1 = memberJpaRepository.findAllQuerydsl();
		assertThat(result1).containsExactly(member);

		List<Member> result2 = memberJpaRepository.findByUsernameQuerydsl(member.getUsername());
		assertThat(result2).containsExactly(member);
	}

}