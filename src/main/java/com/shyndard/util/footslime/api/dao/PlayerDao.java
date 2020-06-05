package com.shyndard.util.footslime.api.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.shyndard.util.footslime.api.entity.Player;
import com.shyndard.util.footslime.api.mapper.PlayerMapper;

@Repository
public class PlayerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Player> getAll() {
		return jdbcTemplate.query("SELECT * FROM player", new PlayerMapper());
	}

	public List<Player> getByTeam(UUID teamId) {
		return jdbcTemplate.query("SELECT * FROM player WHERE team = ?", new Object[] { teamId }, new PlayerMapper());
	}

	public List<Player> getWithNoTeam() {
		return jdbcTemplate.query("SELECT * FROM player WHERE team IS NULL", new PlayerMapper());
	}

	public Optional<Player> getByName(String username) {
		return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM player WHERE name ILIKE ?",
				new Object[] { username }, new PlayerMapper()));
	}

	public void create(String username, UUID team) {
		jdbcTemplate.update("INSERT INTO player (name, team) VALUES (?, ?)", username, team);
	}

	public void updateTeam(Player player, UUID team) {
		jdbcTemplate.update("UPDATE player SET team = ? WHERE name ILIKE ?", team, player.getName());
	}

}