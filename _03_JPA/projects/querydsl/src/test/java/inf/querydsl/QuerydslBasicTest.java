package inf.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import inf.querydsl.dto.MemberDto;
import inf.querydsl.dto.QMemberDto;
import inf.querydsl.dto.UserDto;
import inf.querydsl.entity.Member;
import inf.querydsl.entity.QMember;
import inf.querydsl.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.List;

import static com.querydsl.jpa.JPAExpressions.select;
import static inf.querydsl.entity.QMember.member;
import static inf.querydsl.entity.QTeam.team;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class QuerydslBasicTest {

	@Autowired
	EntityManager em;

	JPAQueryFactory queryFactory;

	@BeforeEach
	void setUp() {
		queryFactory = new JPAQueryFactory(em);

		Team teamA = new Team("teamA");
		Team teamB = new Team("teamB");
		em.persist(teamA);
		em.persist(teamB);

		Member member1 = new Member("member1", 10, teamA);
		Member member2 = new Member("member2", 20, teamA);
		Member member3 = new Member("member3", 30, teamB);
		Member member4 = new Member("member4", 40, teamB);
		em.persist(member1);
		em.persist(member2);
		em.persist(member3);
		em.persist(member4);
	}


	@Test
	void startJPQL() {
		// member1을 찾아라
		String qlString = "select m from Member m " +
				"where m.username = :username";

		Member findMember = em.createQuery(qlString, Member.class)
				.setParameter("username", "member1")
				.getSingleResult();

		assertThat(findMember.getUsername()).isEqualTo("member1");
	}

	@Test
	void startQuerydsl() {
		Member findMember = queryFactory
				.select(member)
				.from(member)
				.where(member.username.eq("member1")) // 파라미터 바인딩 처리
				.fetchOne();

		assertThat(findMember.getUsername()).isEqualTo("member1");
	}

	@Test
	void search() {
		Member findMember = queryFactory
				.select(member)
				.from(member)
				.where(member.username.eq("member1")
						.and(member.age.eq(10)))
				.fetchOne();

		assertThat(findMember.getUsername()).isEqualTo("member1");
	}

	@Test
	void searchAndParam() {
		List<Member> findMembers = queryFactory
				.select(QMember.member)
				.from(QMember.member)
				.where(
						QMember.member.username.eq("member1"),
						QMember.member.age.between(10, 20)
				)
				.fetch();

		assertThat(findMembers.size()).isEqualTo(1);
	}

	@Test
	void resultFetch() {
		List<Member> fetch = queryFactory
				.selectFrom(member)
				.fetch();

		Member fetchOne = queryFactory
				.selectFrom(member)
				.fetchOne();

		Member fetchFirst = queryFactory
				.selectFrom(member)
				.fetchFirst();

		QueryResults<Member> results = queryFactory
				.selectFrom(member)
				.fetchResults();

		results.getTotal();

		// count 쿼리로 변경
		long total = queryFactory
				.selectFrom(member)
				.fetchCount();
	}

	/**
	 * 회원 정렬 순서
	 * 1. 회원 나이 내림차순 (desc)
	 * 2. 회원 이름 올림차순 (asc)
	 * 단 2에서 회원 이름이 없으면 마지에 출력(nulls last)
	 */
	@Test
	void sort() {
		// given
		em.persist(new Member(null, 100));
		em.persist(new Member("member5", 100));
		em.persist(new Member("member6", 100));

		// when
		List<Member> findMembers = queryFactory
				.selectFrom(member)
				.where(member.age.eq(100))
				.orderBy(member.age.desc(), member.username.asc().nullsLast()) // or nullsFirst
				.fetch();

		// then
		Member member5 = findMembers.get(0);
		Member member6 = findMembers.get(1);
		Member memberNull = findMembers.get(2);

		assertThat(member5.getUsername()).isEqualTo("member5");
		assertThat(member6.getUsername()).isEqualTo("member6");
		assertThat(memberNull.getUsername()).isNull();
	}

	@Test
	void paging2() {
		QueryResults<Member> queryResults = queryFactory
				.selectFrom(member)
				.orderBy(member.username.desc())
				.offset(1)
				.limit(2)
				.fetchResults();

		assertThat(queryResults.getTotal()).isEqualTo(4);
		assertThat(queryResults.getLimit()).isEqualTo(2);
		assertThat(queryResults.getOffset()).isEqualTo(1);
		assertThat(queryResults.getResults().size()).isEqualTo(2);
	}


	/**
	 * JPQL
	 * select
	 * COUNT(m), //회원수
	 * SUM(m.age), //나이 합
	 * AVG(m.age), //평균 나이
	 * MAX(m.age), //최대 나이
	 * MIN(m.age) //최소 나이 * from Member m
	 */
	@Test
	@DisplayName("집합 관련 함수")
	void aggregation() {
		List<Tuple> result = queryFactory.select(
						member.count(),
						member.age.sum(),
						member.age.avg(),
						member.age.max(),
						member.age.min()
				)
				.from(member)
				.fetch();

		System.out.println("result = " + result); // result = [[4, 100, 25.0, 40, 10]]

		Tuple tuple = result.get(0);
		assertThat(tuple.get(member.count())).isEqualTo(4);
		assertThat(tuple.get(member.age.sum())).isEqualTo(100);
		assertThat(tuple.get(member.age.avg())).isEqualTo(25);
		assertThat(tuple.get(member.age.max())).isEqualTo(40);
		assertThat(tuple.get(member.age.min())).isEqualTo(10);
	}

	/**
	 * 팀의 이름과 각 팀의 평균 연령을 구해라.
	 */
	@Test
	@DisplayName("GROUP BY, HAVING")
	void group() {
		List<Tuple> result = queryFactory
				.select(team.name, member.age.avg())
				.from(member)
				.join(member.team, team)
				.groupBy(team.name)
				.fetch();

		System.out.println("result = " + result); // result = [[teamA, 15.0], [teamB, 35.0]]

		Tuple teamA = result.get(0);
		Tuple teamB = result.get(1);

		assertThat(teamA.get(team.name)).isEqualTo("teamA");
		assertThat(teamA.get(member.age.avg())).isEqualTo(15);

		assertThat(teamB.get(team.name)).isEqualTo("teamB");
		assertThat(teamB.get(member.age.avg())).isEqualTo(35);
	}

	/**
	 * 팀 A에 소속된 모든 회원
	 */
	@Test
	@DisplayName("조인 - 기본 조인")
	void join() {
		List<Member> result = queryFactory
				.selectFrom(member)
				.join(member.team, team) // join(조인 대상, 별칭으로 사용할 Q타입)
				.where(team.name.eq("teamA"))
				.fetch();

		assertThat(result)
				.extracting("username")
				.containsExactly("member1", "member2");
	}

	/**
	 * 세타 조인(연관관계가 없는 필드로 조인)
	 * 회원의 이름이 팀 이름과 같은 회원 조회
	 */
	@Test
	@DisplayName("세타 조인")
	void thetaJoin() {
		em.persist(new Member("teamA"));
		em.persist(new Member("teamB"));

		List<Member> result = queryFactory
				.select(member)
				.from(member, team)
				.where(member.username.eq(team.name))
				.fetch();

		System.out.println("result = " + result); // result = [Member(id=7, username=teamA, age=0), Member(id=8, username=teamB, age=0)]

		assertThat(result)
				.extracting("username")
				.containsExactly("teamA", "teamB");
	}

	/**
	 * 예) 회원과 팀을 조인하면서, 팀 이름이 teamA인 팀만 조인, 회원은 모두 조회
	 * JPQL: select m, t from Member m left join m.team t on t.name = 'teamA'
	 */
	@Test
	@DisplayName("조인 - ON 절")
	void join_on_filtering() {
		List<Tuple> result = queryFactory
				.select(member, team)
				.from(member)
				.leftJoin(member.team, team).on(team.name.eq("teamA"))
				.fetch();

		for (Tuple tuple : result) {
			System.out.println("tuple = " + tuple);
		}
	}

	/**
	 * 연관관계 없는 엔티티 외부 조인
	 * 회원의 이름이 팀 이름과 같은 대상 외부 조인
	 */
	@Test
	@DisplayName("연관관계 없는 엔티티 외부 조인")
	void join_on_no_relation() {
		em.persist(new Member("teamA"));
		em.persist(new Member("teamB"));
		em.persist(new Member("teamC"));

		List<Tuple> result = queryFactory
				.select(member, team)
				.from(member)
				.leftJoin(team).on(member.username.eq(team.name))
				.fetch();

		for (Tuple tuple : result) {
			System.out.println("tuple = " + tuple);
		}
	}

	@PersistenceUnit
	EntityManagerFactory emf;

	@Test
	@DisplayName("페치조인이 없을 때")
	void no_fetchJoin() {
		em.flush();
		em.clear();

		Member findMember = queryFactory
				.selectFrom(member)
				.where(member.username.eq("member1"))
				.fetchOne();

		boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam()); // LAZY 로딩이라 로딩이 안 되었다.
		assertThat(loaded).as("페치조인 미적용").isFalse();
	}

	@Test
	@DisplayName("페치 조인")
	void fetchJoin() {
		em.flush();
		em.clear();

		Member findMember = queryFactory
				.selectFrom(member)
				.join(member.team, team).fetchJoin()
				.where(member.username.eq("member1"))
				.fetchOne();

		boolean loaded = emf.getPersistenceUnitUtil().isLoaded(findMember.getTeam()); // LAZY 로딩이라 로딩이 안 되었다.
		assertThat(loaded).as("페치 조인 적용").isTrue();
	}

	/**
	 * 나이가 가장 많은 회원 조회
	 */
	@Test
	@DisplayName("서브 쿼리")
	void subQuery() {

		/**
		 * 서브 쿼리를 사용하기 때문에 alias 가 중복되면 안 됨.
		 * -> 새로운 QType 객체를 생성하여 alias 의 중복을 막는다.
		 */

		QMember memberSub = new QMember("memberSub");

		List<Member> result = queryFactory
				.selectFrom(member)
				.where(member.age.eq(
						select(memberSub.age.max())
								.from(memberSub)
				))
				.fetch();

		assertThat(result)
				.extracting("age")
				.containsExactly(40);
	}

	/**
	 * 나이가 평균 이상인 회원 조회
	 */
	@Test
	@DisplayName("서브 쿼리")
	void subQueryGoe() {

		QMember memberSub = new QMember("memberSub");

		List<Member> result = queryFactory
				.selectFrom(member)
				.where(member.age.goe(
						select(memberSub.age.avg())
								.from(memberSub)
				))
				.fetch();

		assertThat(result)
				.extracting("age")
				.containsExactly(30, 40);
	}

	/**
	 * 나이가 10살 이상인 회원 조회
	 */
	@Test
	@DisplayName("서브 쿼리 In")
	void subQueryIn() {

		QMember memberSub = new QMember("memberSub");

		// 효율적이지 않은 쿼리인데 예제를 위해 이렇게 작성

		List<Member> result = queryFactory
				.selectFrom(member)
				.where(member.age.in(
						select(memberSub.age)
								.from(memberSub)
								.where(member.age.gt(10))
				))
				.fetch();


		assertThat(result)
				.extracting("age")
				.containsExactly(20, 30, 40);
	}

	@Test
	@DisplayName("SELECT 절 서브쿼리")
	void subQueryAtSelect() {

		QMember memberSub = new QMember("memberSub");

		List<Tuple> result = queryFactory
				.select(member.username,
						select(memberSub.age.avg())
								.from(memberSub)
				)
				.from(member)
				.fetch();

		for (Tuple tuple : result) {
			System.out.println("tuple = " + tuple);
		}
	}

	/**
	 * Case 문
	 */
	@Test
	@DisplayName("Simple Case")
	void simpleCase() {
		List<String> result = queryFactory
				.select(member.age
						.when(10).then("열살")
						.when(20).then("스무살")
						.otherwise("기타"))
				.from(member)
				.fetch();

		System.out.println("result = " + result); // result = [열살, 스무살, 기타, 기타]

		for (String s : result) {
			System.out.println("s = " + s);
		}
	}

	@Test
	@DisplayName("Complex Case")
	void complexCase() {
		List<String> result = queryFactory
				.select(new CaseBuilder()
						.when(member.age.between(0, 20)).then("0~20살")
						.when(member.age.between(21, 40)).then("21~40살")
						.otherwise("기타")
				)
				.from(member)
				.fetch();

		System.out.println("result = " + result); // result = [0~20살, 0~20살, 21~40살, 21~40살]
	}

	@Test
	void constant() {
		List<Tuple> result = queryFactory
				.select(member.username, Expressions.constant("A"))
				.from(member)
				.fetch();

		for (Tuple tuple : result) {
			System.out.println("tuple = " + tuple);
		}
	}

	@Test
	@DisplayName("concat")
	void concat() {
		// {username}_{age}
		List<String> result = queryFactory
				.select(member.username.concat("_").concat(member.age.stringValue()))
				.from(member)
				.where(member.username.eq("member1"))
				.fetch();

		for (String s : result) {
			System.out.println("s = " + s);
		}
	}

	@Test
	@DisplayName("프로젝션 대상이 하나")
	void simpleProjection() {
		List<String> result = queryFactory
				.select(member.username)
				.from(member)
				.fetch();

		for (String s : result) {
			System.out.println("s = " + s);
		}
	}

	@Test
	@DisplayName("프로젝션 - 튜플 조회")
	void tupleProjection() {
		List<Tuple> result = queryFactory
				.select(member.username, member.age)
				.from(member)
				.fetch();

		for (Tuple tuple : result) {
			String username = tuple.get(member.username);
			Integer age = tuple.get(member.age);
			System.out.println("username = " + username);
			System.out.println("age = " + age);
		}
	}

	@Test
	@DisplayName("JPQL을 이용한 DTO 조회")
	void findDtoByJpql() {
		List<MemberDto> result = em.createQuery("select new inf.querydsl.dto.MemberDto(m.username, m.age) " +
						"from Member m", MemberDto.class)
				.getResultList();

		for (MemberDto memberDto : result) {
			System.out.println("memberDto = " + memberDto);
		}
	}

	@Test
	@DisplayName("Querydsl DTO 조회 - Setter")
	void findDtoBySetter() {
		List<MemberDto> result = queryFactory
				.select(Projections.bean(MemberDto.class,
						member.username,
						member.age))
				.from(member)
				.fetch();

		for (MemberDto memberDto : result) {
			System.out.println("memberDto = " + memberDto);
		}
	}

	@Test
	@DisplayName("Querydsl DTO 조회 - Field ")
	void findDtoByField() {
		List<MemberDto> result = queryFactory
				.select(Projections.fields(MemberDto.class,
						member.username,
						member.age))
				.from(member)
				.fetch();

		for (MemberDto memberDto : result) {
			System.out.println("memberDto = " + memberDto);
		}
	}

	@Test
	@DisplayName("Querydsl DTO 조회 - 생성자 ")
	void findDtoByConstructor() {
		List<MemberDto> result = queryFactory
				.select(Projections.constructor(MemberDto.class,
						member.username,
						member.age))
				.from(member)
				.fetch();

		for (MemberDto memberDto : result) {
			System.out.println("memberDto = " + memberDto);
		}
	}

	@Test
	@DisplayName("필드이름이 맞지 않는 경우 as 사용")
	void findUserDto() {
		QMember memberSub = new QMember("memberSub");

		List<UserDto> result = queryFactory
				.select(Projections.fields(UserDto.class,
						member.username.as("name"),
						ExpressionUtils.as(JPAExpressions
								.select(memberSub.age.max())
								.from(memberSub), "age")
				))
				.from(member)
				.fetch();

		for (UserDto userDto : result) {
			System.out.println("userDto = " + userDto);
		}
	}

	@Test
	@DisplayName("DTO 조회 - @QueryProjection")
	void findDtoByQueryProjection() {
		List<MemberDto> result = queryFactory
				.select(new QMemberDto(member.username, member.age))
				.from(member)
				.fetch();

		for (MemberDto memberDto : result) {
			System.out.println("memberDto = " + memberDto);
		}
	}

	@Test
	@DisplayName("동적 쿼리 - BooleanBuilder")
	void dynamicQuery_BooleanBuilder() {
		// given
		String usernameParam = "member1";
		Integer ageParam = null;

		// when
		List<Member> result = searchMember1(usernameParam, ageParam);

		// then
		assertThat(result.size()).isEqualTo(1);
	}

	/**
	 * 조건 Cond가 null 이면 검색 조건 포함 X
	 */
	private List<Member> searchMember1(String usernameCond, Integer ageCond) { // usernameCondition

		BooleanBuilder builder = new BooleanBuilder();

		if (usernameCond != null) {
			builder.and(member.username.eq(usernameCond));
		}

		if (ageCond != null) {
			builder.and(member.age.eq(ageCond));
		}

		return queryFactory
				.selectFrom(member)
				.where(builder)
				.fetch();
	}

	@Test
	@DisplayName("동적 쿼리 - Where 다중 파라미터")
	void dynamicQuery_WhereParam() {
		// given
		String usernameParam = "member1";
		Integer ageParam = 10;

		// when
		List<Member> result = searchMember2(usernameParam, ageParam);

		// then
		assertThat(result.size()).isEqualTo(1);
	}

	private List<Member> searchMember2(String usernameCond, Integer ageCond) {
		return queryFactory
				.selectFrom(member)
//				.where(usernameEq(usernameCond), ageEq(ageCond)) // where 절에 null 이 들어오면 조건이 무시된다.
				.where(allEq(usernameCond, ageCond))
				.fetch();
	}

	private BooleanExpression usernameEq(String usernameCond) {
		// 간단한 경우에는 삼항 연산자 선호
		return usernameCond == null ? null : member.username.eq(usernameCond);
	}

	private BooleanExpression ageEq(Integer ageCond) {
		return ageCond == null ? null : member.age.eq(ageCond);
	}

	private BooleanExpression allEq(String usernameCond, Integer ageCond) {
		return usernameEq(usernameCond).and(ageEq(ageCond));
	}

	/**
	 * WHERE 다중 파라미터를 이용하여 컴포지션이 가능하다.
	 * - 광고 상태가 isVaild, 날짜가 IN -> isServiceable 이라는 메서드 작성
	 */

	@Test
	@DisplayName("수정 벌크 연산")
	@Commit
	void bulkUpdate() {

		// member1 = 10 -> DB member1
		// member2 = 20 -> DB member2
		// member3 = 30 -> DB member3
		// member4 = 40 -> DB member4
		long count = queryFactory
				.update(member)
				.set(member.username, "비회원")
				.where(member.age.lt(28))
				.execute();

		em.flush();
		em.clear();

		// 1 member1 = 10 -> 1 DB 비회원
		// 2 member2 = 20 -> 2 DB 비회원
		// 3 member3 = 30 -> 3 DB member3
		// 4 member4 = 40 -> 4 DB member4

		// 영속성 컨텍스트에 같은 엔티티가 있으면, DB에서 가져온 결과를 버린다.
		List<Member> result = queryFactory
				.selectFrom(member)
				.fetch();

		for (Member memberElem : result) {
			System.out.println("memberElem = " + memberElem);
		}
	}

	@Test
	@DisplayName("기존 숫자에 -1 더하기")
	void bulkAdd() {
		long count = queryFactory
				.update(member)
				.set(member.age, member.age.add(-1))
				.execute();

		System.out.println("count = " + count);
	}

	@Test
	@DisplayName("벌크 삭제")
	void bulkDelete() {
		long executedCount = queryFactory
				.delete(member)
				.where(member.age.gt(18))
				.execute();

		System.out.println("executedCount = " + executedCount);
	}

}
