package com.shyndard.util.footslime.api.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shyndard.util.footslime.api.entity.Team;
import com.shyndard.util.footslime.api.mapper.TeamMapper;

@Repository
public class TeamDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PlayerDao playerDao;

	public List<Team> getAll() {
		return jdbcTemplate.query("SELECT * FROM team", new TeamMapper()).stream().map(team -> {
			return team.setPlayers(playerDao.getByTeam(team.getId()));
		}).collect(Collectors.toList());
	}

	public Optional<Team> getById(UUID teamId) {
		final Team team = jdbcTemplate.queryForObject("SELECT * FROM team WHERE id = ?", new Object[] { teamId }, new TeamMapper());
		return team == null ? Optional.empty() : Optional.of(team.setPlayers(playerDao.getByTeam(teamId)));
	}

	public void create(Team team, List<String> usernames) throws SQLException {
		jdbcTemplate.update("INSERT INTO team (id, name) VALUES (?, ?)", team.getId(), team.getName());
		usernames.forEach(username -> {
			playerDao.create(username, team.getId());
		});
	}

}
