package inf.querydsl.controller;

import inf.querydsl.dto.MemberSearchCondition;
import inf.querydsl.dto.MemberTeamDto;
import inf.querydsl.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberJpaRepository memberJpaRepository;

	@GetMapping("/v1/members")
	public List<MemberTeamDto> searchMemberV1(@ModelAttribute MemberSearchCondition condition) { // ModelAttribute 생략 가능
		return memberJpaRepository.search(condition);
	}

}
