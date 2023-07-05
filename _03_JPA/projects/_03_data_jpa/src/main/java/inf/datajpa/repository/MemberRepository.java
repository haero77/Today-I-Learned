package inf.datajpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import inf.datajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
