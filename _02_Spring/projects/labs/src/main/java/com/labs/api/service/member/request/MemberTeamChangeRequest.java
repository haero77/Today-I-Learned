package com.labs.api.service.member.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class MemberTeamChangeRequest {

	private Long memberId;
	private Long teamId;

	public MemberTeamChangeRequest(Long memberId, Long teamId) {
		this.memberId = memberId;
		this.teamId = teamId;
	}

}
