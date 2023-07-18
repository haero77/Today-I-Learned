package com.labs.api.service.member.respoonse;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberTeamChangeResponse {

	private Long memberId;
	private Long teamId;

	public MemberTeamChangeResponse(Long memberId, Long teamId) {
		this.memberId = memberId;
		this.teamId = teamId;
	}

}
