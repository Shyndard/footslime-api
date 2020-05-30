package com.shyndard.util.footslime.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyndard.util.footslime.api.dao.TeamDao;
import com.shyndard.util.footslime.api.entity.Team;
import com.shyndard.util.footslime.api.entity.dto.TeamCreationDto;

@Service
public class TeamService {

	@Autowired
	private TeamDao teamDao;

	public Optional<Team> create(TeamCreationDto dto) {
		try {
			final Team team = new Team(dto.getName());
			teamDao.create(team, dto.getUsernames());
			return teamDao.getById(team.getId());
		} catch (final Exception ex) {
			ex.printStackTrace();
			return Optional.empty();
		}
	}

}
