package com.shyndard.util.footslime.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shyndard.util.footslime.api.dao.PlayerDao;
import com.shyndard.util.footslime.api.entity.Player;
import com.shyndard.util.footslime.api.entity.dto.UserCreationDto;

@Service
public class PlayerService {

	@Autowired
	private PlayerDao playerDao;

	public Optional<Player> create(UserCreationDto dto) {
		try {
			playerDao.create(dto.getName(), null);
			return playerDao.getByName(dto.getName());
		} catch (final Exception ex) {
			ex.printStackTrace();
			return Optional.empty();
		}
	}

}
