package com.shyndard.util.footslime.api.service;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyndard.util.footslime.api.dao.MatchDao;
import com.shyndard.util.footslime.api.entity.Match;

@Service
public class MatchService {

	private static final List<String> ALLOWED_KEY = Arrays.asList("add", "remove");

	@Autowired
	private MatchDao matchDao;

	public Optional<Match> startMatch(int matchId) {
		matchDao.updateStarting(matchId);
		return matchDao.getById(matchId);
	}

	public Optional<Match> stopMatch(int matchId) {
		matchDao.updateEnding(matchId);
		return matchDao.getById(matchId);
	}

	public Optional<Match> teamScore(int matchId, String teamColor, SimpleEntry<String, Integer> json) {
		if (ALLOWED_KEY.contains(json.getKey())) {
			if ("red".equals(teamColor)) {
				matchDao.updateRedPoint(matchId, "add".equalsIgnoreCase(json.getKey()) ? +json.getValue() : -json.getValue());
				return matchDao.getById(matchId);
			} else if ("blue".equals(teamColor)) {
				matchDao.updateBluePoint(matchId, "remove".equalsIgnoreCase(json.getKey()) ? +json.getValue() : -json.getValue());
				return matchDao.getById(matchId);
			}
		}
		return Optional.empty();
	}
}
