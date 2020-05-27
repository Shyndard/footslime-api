package com.shyndard.util.footslime.api.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.shyndard.util.footslime.api.dao.PlayerDao;
import com.shyndard.util.footslime.api.entity.Player;

@RestController
public class PlayerController {

	@Autowired
	private PlayerDao playerDao;

	// Get all match
	@GetMapping(value = "/players", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Player> getAll() {
		return playerDao.getAll();
	}

	// Get all match
	@GetMapping(value = "/players/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBydId(@PathVariable String username) {
		final Optional<Player> player = playerDao.getByName(username);
		if (player.isPresent()) {
			return new ResponseEntity<>(player.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
