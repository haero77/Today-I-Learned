package inf.datajpa.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import inf.datajpa.dto.MemberDto;
import inf.datajpa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

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

	Page<Member> findAsPageByAge(int age, Pageable pageable);

	Slice<Member> findAsSliceByAge(int age, Pageable pageable);

	List<Member> findAsListByAge(int age, Pageable pageable);

	// 카운트 쿼리와 페치 조인 못 쓴다.
	// @Query(value = "select m from Member m join fetch m.team t")
	// Page<Member> findWithFetchJoinWithoutCountQueryByAge(int age, Pageable pageable);

	// Page 리턴 타입 때문에 COUNT 쿼리가 추가로 실행 될 때, COUNT 쿼리에서도 JOIN이 실행되는 문제
	@Query(value = "select m from Member m left join m.team t")
	Page<Member> findWithoutCountQueryByAge(int age, Pageable pageable);

	@Query(value = "select m from Member m left join m.team t",
		countQuery = "select count(m) from Member m")
	Page<Member> findWithCountQueryByAge(int age, Pageable pageable);

	@Modifying(clearAutomatically = true)
	@Query("update Member m set m.age = m.age + 1 where m.age >= :age")
	int bulkAgePlus(@Param("age") int age);

	@Query("select m from Member m left join fetch m.team")
	List<Member> findMemberFetchJoin();

	@Override
	@EntityGraph(attributePaths = {"team"})
	List<Member> findAll();

	@EntityGraph(attributePaths = {"team"})
	@Query("select m from Member m")
	List<Member> findEntityGraphAll();

	@EntityGraph(attributePaths = {"team"})
	List<Member> findEntityGraphByUsername(@Param("username") String username);

	Optional<Member> findByUsername(String username);

	@QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true"))
	Optional<Member> findReadOnlyByUsername(String username);

	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<Member> findLockByUsername(String username);

}
