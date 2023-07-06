package inf.datajpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import inf.datajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

	List<Member> findTop3HelloBy();
}
