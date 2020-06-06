package com.shyndard.util.footslime.api.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shyndard.util.footslime.api.entity.Match;
import com.shyndard.util.footslime.api.mapper.MatchMapper;

@Repository
public class MatchDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PlayerDao playerDao;

	public List<Match> getAll() {
		return jdbcTemplate.query("SELECT m.*, tb.name AS blue_team_name, tr.name AS red_team_name FROM match as m JOIN team as tb ON tb.id = blue_team JOIN team as tr ON tr.id = red_team", new MatchMapper()).stream().map(match -> {
			return match.setPlayersRedTeam(playerDao.getByTeam(match.getRedTeamId())).setPlayersBlueTeam(playerDao.getByTeam(match.getBlueTeamId()));
		}).collect(Collectors.toList());
	}

	public int create(UUID blueTeamId, UUID redTeamId) {
		return jdbcTemplate.update("INSERT INTO match (id, blue_team, red_team) VALUES (?, ?, ?)", UUID.randomUUID(), blueTeamId, redTeamId);
	}

	public int updateStarting(int id) {
		return jdbcTemplate.update("UPDATE match SET start_at = CURRENT_TIMESTAMP WHERE id = ?", id);
	}

	public int updateEnding(int id) {
		return jdbcTemplate.update("UPDATE match SET end_at = CURRENT_TIMESTAMP WHERE id = ?", id);
	}

	public int updateRedPoint(int id, int value) {
		return jdbcTemplate.update("UPDATE match SET red_point = red_point + ? WHERE id = ?", value, id);
	}

	public int updateBluePoint(int id, int value) {
		return jdbcTemplate.update("UPDATE match SET blue_point = blue_point + ? WHERE id = ?", value, id);
	}

	public int reset(int id) {
		return jdbcTemplate.update("UPDATE match SET blue_point = 0, red_point = 0, start_at = 0, end_at = 0 WHERE id = ?", id);
	}

	public Optional<Match> getById(int matchId) {
		final List<Match> list = jdbcTemplate.query("SELECT * FROM match WHERE id = ?", new Object[] { matchId }, new MatchMapper());
		return list.size() == 1 ? Optional.of(list.get(0)) : Optional.empty();
	}
}