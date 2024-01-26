package inf.querydsl.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inf.querydsl.dto.MemberSearchCondition;
import inf.querydsl.dto.MemberTeamDto;
import inf.querydsl.dto.QMemberTeamDto;
import inf.querydsl.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static inf.querydsl.entity.QMember.member;
import static inf.querydsl.entity.QTeam.team;
import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {

	private final EntityManager em;
	private final JPAQueryFactory queryFactory;

	public void save(Member member) {
		em.persist(member);
	}

	public Optional<Member> findById(Long id) {
		Member findMember = em.find(Member.class, id);
		return Optional.ofNullable(findMember);
	}

	public List<Member> findAll() {
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}

	public List<Member> findAllQuerydsl() {
		return queryFactory
				.selectFrom(member) // QMember.member 에서 QMember 를 static import
				.fetch();
	}

	public List<Member> findByUsername(String username) {
		return em.createQuery("select m from Member m where m.username = :username", Member.class)
				.setParameter("username", username)
				.getResultList();
	}

	public List<Member> findByUsernameQuerydsl(String username) {
		return queryFactory
				.selectFrom(member)
				.where(member.username.eq(username))
				.fetch();
	}

	public List<MemberTeamDto> searchByBuilder(MemberSearchCondition condition) {

		BooleanBuilder builder = new BooleanBuilder();

		// null & Blank("") 방지
		if (hasText(condition.getUsername())) {
			builder.and(member.username.eq(condition.getUsername()));
		}

		if (hasText(condition.getTeamName())) {
			builder.and(team.name.eq(condition.getTeamName()));
		}

		if (condition.getAgeGoe() != null) {
			builder.and(member.age.goe(condition.getAgeGoe()));
		}

		if (condition.getAgeLoe() != null) {
			builder.and(member.age.loe(condition.getAgeLoe()));
		}

		// 성능 최적화: DTO로 한 번에 조회
		return queryFactory
				.select(new QMemberTeamDto(
						member.id.as("memberId"),
						member.username,
						member.age,
						team.id.as("teamId"),
						team.name.as("teamName")))
				.from(member)
				.leftJoin(member.team, team)
				.where(builder)
				.fetch();
	}

	public List<MemberTeamDto> search(MemberSearchCondition condition) {
		return queryFactory
				.select(new QMemberTeamDto(
						member.id.as("memberId"),
						member.username,
						member.age,
						team.id.as("teamId"),
						team.name.as("teamName")))
				.from(member)
				.leftJoin(member.team, team)
				.where(
						usernameEq(condition.getUsername()),
						teamNameEq(condition.getTeamName()),
						ageGoe(condition.getAgeGoe()),
						ageLoe(condition.getAgeLoe())
				)
				.fetch();
	}

	/**
	 * 엔티티 조회
	 */
	public List<Member> searchMember(MemberSearchCondition condition) {
		return queryFactory
				.selectFrom(member)
				.leftJoin(member.team, team)
				.where(isValid(condition))
				.fetch();
	}

	/**
	 * TODO: NPE 발생 원인 분석
	 */
	public BooleanExpression isValid(MemberSearchCondition condition) {
		return usernameEq(condition.getUsername())
				.and(teamNameEq(condition.getTeamName()))
				.and(ageGoe(condition.getAgeGoe()))
				.and(ageLoe(condition.getAgeLoe()));
	}

	public BooleanExpression ageBetween(int ageGoe, int ageLoe) {
		return ageGoe(ageGoe).and(ageLoe(ageLoe));
	}

	/**
	 * Predicate 보다 BooleanExpression 이 낫다.
	 * 👉다른 Expression 들과 조합(Composition)이 가능하기 때문
	 */
	private BooleanExpression usernameEq(String username) {
		return hasText(username) ? member.username.eq(username) : null;
	}

	private BooleanExpression teamNameEq(String teamName) {
		return hasText(teamName) ? team.name.eq(teamName) : null;
	}

	private BooleanExpression ageGoe(Integer ageGoe) {
		return ageGoe != null ? member.age.goe(ageGoe) : null;
	}

	private BooleanExpression ageLoe(Integer ageLoe) {
		return ageLoe != null ? member.age.loe(ageLoe) : null;
	}

}
