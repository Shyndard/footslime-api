package com.shyndard.util.footslime.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TokenDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public boolean isValid(String token) {
		return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM token WHERE data = ?", new Object[] {token}, Integer.class) == 1;
	}

}