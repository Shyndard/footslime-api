package com.shyndard.util.footslime.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyndard.util.footslime.api.dao.MatchDao;
import com.shyndard.util.footslime.api.entity.Match;

@Service
public class MatchService {

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

	public Optional<Match> teamScore(int matchId, String teamColor, int value) {
		if ("red".equalsIgnoreCase(teamColor)) {
			matchDao.updateRedPoint(matchId, value);
			return matchDao.getById(matchId);
		} else if ("blue".equalsIgnoreCase(teamColor)) {
			matchDao.updateBluePoint(matchId, value);
			return matchDao.getById(matchId);
		}
		return Optional.empty();
	}
}
