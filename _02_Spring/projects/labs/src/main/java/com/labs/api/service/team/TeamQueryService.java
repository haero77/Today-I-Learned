package com.labs.api.service.team;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.labs.domain.team.Team;
import com.labs.domain.team.TeamRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TeamQueryService {

	private final TeamRepository teamRepository;

	public Team findTeamById(Long id) {
		return teamRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("There is no team matching the given id."));
	}


}
