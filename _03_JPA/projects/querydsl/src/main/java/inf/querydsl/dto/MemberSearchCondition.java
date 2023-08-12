package inf.querydsl.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
//@Setter
public class MemberSearchCondition {

	// 회원명, 팀명, 나이(ageGoe, ageLoe)
	private String username;
	private String teamName;
	private Integer ageGoe;
	private Integer ageLoe;

}
