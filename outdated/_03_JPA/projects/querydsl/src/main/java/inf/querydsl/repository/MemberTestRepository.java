package inf.querydsl.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import inf.querydsl.dto.MemberSearchCondition;
import inf.querydsl.entity.Member;
import inf.querydsl.repository.support.Querydsl4RepositorySupport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static inf.querydsl.entity.QMember.member;
import static inf.querydsl.entity.QTeam.team;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class MemberTestRepository extends Querydsl4RepositorySupport {

    public MemberTestRepository(Class<?> domainClass) {
        super(domainClass);
    }

    public List<Member> basicSelect() {
        return super.select(member)
                .from(member)
                .fetch();
    }

    public List<Member> basicSelectFrom() {
        return super.selectFrom(member)
                .fetch();
    }

    // 지원 클래스 사용 X
    public Page<Member> searchPageByApplyPage(MemberSearchCondition condition, Pageable pageable) {
        JPAQuery<Member> query = selectFrom(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe())
                );

        List<Member> content = super.getQuerydsl().applyPagination(pageable, query)
                .fetch();

        return PageableExecutionUtils.getPage(content, pageable, query::fetchCount);
    }

    // 지원 클래스 사용 O
    public Page<Member> applyPagination(MemberSearchCondition condition, Pageable pageable) {
        return super.applyPagination(pageable, query -> query
                .selectFrom(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe())
                )
        );
    }

    // 카운트 쿼리 분리
    public Page<Member> applyPagination2(MemberSearchCondition condition, Pageable pageable) {
        return super.applyPagination(
                pageable,
                contentQuery -> contentQuery
                        .selectFrom(member)
                        .leftJoin(member.team, team)
                        .where(
                                usernameEq(condition.getUsername()),
                                teamNameEq(condition.getTeamName()),
                                ageGoe(condition.getAgeGoe()),
                                ageLoe(condition.getAgeLoe())
                        ),
                countQuery -> countQuery
                        .select(member.id)
                        .from(member)
                        .leftJoin(member.team, team)
                        .where(
                                usernameEq(condition.getUsername()),
                                teamNameEq(condition.getTeamName()),
                                ageGoe(condition.getAgeGoe()),
                                ageLoe(condition.getAgeLoe())
                        )
        );
    }

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
