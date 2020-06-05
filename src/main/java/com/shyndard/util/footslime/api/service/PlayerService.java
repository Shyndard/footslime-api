package com.shyndard.util.footslime.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyndard.util.footslime.api.dao.PlayerDao;
import com.shyndard.util.footslime.api.entity.Player;

@Service
public class PlayerService {

	@Autowired
	private PlayerDao playerDao;

	public Optional<Player> create(String username) {
		try {
			playerDao.create(username, null);
			return playerDao.getByName(username);
		} catch (final Exception ex) {
			ex.printStackTrace();
			return Optional.empty();
		}
	}

}
