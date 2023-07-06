package inf.datajpa.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import inf.datajpa.dto.MemberDto;
import inf.datajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

	List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

	List<Member> findTop3HelloBy();

	@Query("select m from Member m where m.username = :username and m.age = :age")
	Optional<Member> findUser(@Param("username") String username, @Param("age") int age);

	// 단순히 값 하나를 조회
	@Query("select m.username from Member m")
	List<String> findUsernameList();

	// DTO로 직접 조회
	@Query("select new inf.datajpa.dto.MemberDto(m.id, m.username, t.name) "
		+ "from Member m "
		+ "join m.team t")
	List<MemberDto> findMemberDto();

	@Query("select m from Member m where m.username in :names")
	List<Member> findByNames(@Param("names") Collection<String> names);

}
