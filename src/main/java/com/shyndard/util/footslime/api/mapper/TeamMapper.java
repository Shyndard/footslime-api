package com.shyndard.util.footslime.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;

import com.shyndard.util.footslime.api.entity.Team;

public class TeamMapper implements RowMapper<Team> {

	@Override
	public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Team team = new Team();
		team.setId(rs.getObject("id", UUID.class));
		team.setName(rs.getString("name"));
		return team;
	}

}
