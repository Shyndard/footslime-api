package com.shyndard.util.footslime.api.control;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyndard.util.footslime.api.dao.TeamDao;
import com.shyndard.util.footslime.api.entity.Team;
import com.shyndard.util.footslime.api.entity.dto.TeamCreationDto;
import com.shyndard.util.footslime.api.service.TeamService;

@RestController
public class TeamController {

	@Autowired
	private TeamDao teamDao;

	@Autowired
	private TeamService teamService;

	// Get all match
	@GetMapping(value = "/teams", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Team> getAll() {
		return teamDao.getAll();
	}

	// Get all match
	@GetMapping(value = "/teams/{teamId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getBydId(@PathVariable UUID teamId) {
		final Optional<Team> team = teamDao.getById(teamId);
		if (team.isPresent()) {
			return new ResponseEntity<>(team, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping(value = "/teams")
	public ResponseEntity<?> create(@RequestBody TeamCreationDto dto) {
		final Optional<Team> team = teamService.create(dto);
		if (team.isPresent()) {
			return new ResponseEntity<>(team.get(), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
}