package com.shyndard.util.footslime.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyndard.util.footslime.api.dao.PlayerDao;
import com.shyndard.util.footslime.api.dao.TeamDao;
import com.shyndard.util.footslime.api.entity.Player;
import com.shyndard.util.footslime.api.entity.Team;
import com.shyndard.util.footslime.api.entity.dto.TeamCreationDto;

@Service
public class TeamService {

	@Autowired
	private TeamDao teamDao;

	@Autowired
	private PlayerDao playerDao;

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

	public boolean autoCreateTeam() {
		final List<Player> players = playerDao.getWithNoTeam();
		boolean created = false;
		if (players.size() == 4) {
			try {
				final Team team = new Team(generateRandomTeamName());
				teamDao.create(team, new ArrayList<>());
				players.stream().forEach(player -> {
					playerDao.updateTeam(player, team.getId());
				});
				created = true;
			} catch (final Exception ex) {
				ex.printStackTrace();
			}
		}
		return created;
	}

	private String generateRandomTeamName() {
		return new Random().ints(97, 122 + 1).limit(5).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString() + " team";
	}
}
