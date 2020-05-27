package com.shyndard.util.footslime.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;

import com.shyndard.util.footslime.api.entity.Player;

public class PlayerMapper implements RowMapper<Player> {

	@Override
	public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Player player = new Player();
		player.setName(rs.getString("name"));
		player.setTeamId(rs.getObject("team", UUID.class));
		return player;
	}

}
