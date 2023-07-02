package inf.datajpa.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

import inf.datajpa.entity.member.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
