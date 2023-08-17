package inf.querydsl.repository;

import inf.querydsl.dto.MemberSearchCondition;
import inf.querydsl.dto.MemberTeamDto;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> search(MemberSearchCondition condition);

}
