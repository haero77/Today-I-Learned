package inf.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;

public class MemberDto {

	private final String username;
	private final int age;

	@QueryProjection
	public MemberDto(String username, int age) {
		this.username = username;
		this.age = age;
	}

}
