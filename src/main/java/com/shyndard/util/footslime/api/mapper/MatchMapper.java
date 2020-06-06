package com.shyndard.util.footslime.api.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.shyndard.util.footslime.api.entity.Match;

public class MatchMapper implements RowMapper<Match> {

	@Override
	public Match mapRow(ResultSet rs, int rowNum) throws SQLException {
		final Match match = new Match();
		match.setId(rs.getInt("id"));
		match.setStartAt(rs.getTimestamp("start_at"));
		match.setEndAt(rs.getTimestamp("end_at"));
		match.setRedTeamId(rs.getObject("red_team", java.util.UUID.class));
		match.setRedTeamPoint(rs.getInt("red_point"));
		match.setBlueTeamId(rs.getObject("blue_team", java.util.UUID.class));
		match.setBlueTeamPoint(rs.getInt("blue_point"));
		return match;
	}

}
