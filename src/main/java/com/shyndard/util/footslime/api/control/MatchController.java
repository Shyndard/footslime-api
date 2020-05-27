package com.shyndard.util.footslime.api.control;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyndard.util.footslime.api.dao.MatchDao;
import com.shyndard.util.footslime.api.entity.Match;
import com.shyndard.util.footslime.api.service.MatchService;

@RestController
public class MatchController {

	@Autowired
	private MatchDao matchDao;

	@Autowired
	private MatchService matchService;

	// Get all match
	@GetMapping(value = "/matchs", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Match> getAll() {
		return matchDao.getAll();
	}

	// Get all match started
	@GetMapping(value = "/matchs?inprogress=true", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Match> getAllInProgress() {
		return matchDao.getInProgress();
	}

	@GetMapping(value = "/matchs?waitingtostart=true", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Match> getAllWaitingToStart() {
		return matchDao.getNotStarted();
	}

	// Get all match ended
	@GetMapping(value = "/matchs?ended=true", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Match> getAllEnded() {
		return matchDao.getEnded();
	}

	// Start a match
	@PutMapping(value = "/matchs/{matchId}/start", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Match> startMatch(@PathVariable int matchId) {
		final Optional<Match> match = matchService.startMatch(matchId);
		if (match.isPresent()) {
			return new ResponseEntity<>(match.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	// End a match
	@PutMapping(value = "/matchs/{matchId}/end", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Match> endMatch(@PathVariable int matchId) {
		final Optional<Match> match = matchService.stopMatch(matchId);
		if (match.isPresent()) {
			return new ResponseEntity<>(match.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

	}

	// A team score
	@PutMapping(value = "/matchs/{matchId}/score/{teamColor}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Match> teamScore(@PathVariable int matchId, @PathVariable String teamColor, @RequestBody SimpleEntry<String, Integer> json) {
		final Optional<Match> match = matchService.teamScore(matchId, teamColor, json);
		if (match.isPresent()) {
			return new ResponseEntity<>(match.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}