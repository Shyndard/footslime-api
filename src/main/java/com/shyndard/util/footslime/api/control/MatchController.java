package com.shyndard.util.footslime.api.control;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shyndard.util.footslime.api.entity.Match;

@RestController
public class MatchController {

	// Persistent data not needed
	private static Map<String, Match> matchList = new HashMap<>();
	
	// Get all match
	@GetMapping(value = "/matchs", produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<Match> getAllMatch() {
		return matchList.values();
	}
	
	// Create a new match
	@PostMapping(value = "/matchs", produces = MediaType.APPLICATION_JSON_VALUE)
	public Match startMatch() {
		Match match = new Match(new Date());
		matchList.put(match.getId().toString(), match);
		return match;
	}
	
	// Update a match
	// TODO: Need to be refactored
	@PutMapping(value = "/matchs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Match endMatch(@PathVariable(value = "id") UUID matchId) {
		Match match = matchList.get(matchId.toString());
		return match == null ? null : match.setEndAt(new Date());
	}
	
	// Update team score of a match
	@PutMapping(value = "/matchs/{id}/score", produces = MediaType.APPLICATION_JSON_VALUE)
	public Match oneTeamScore(@PathVariable(value = "id") UUID matchId, @RequestBody Map<String, String> team) {
		Match match = matchList.get(matchId.toString());
		return match == null || team.size() != 1 && !"team".equalsIgnoreCase(team.keySet().iterator().next()) ? null : match.addPointToTeam(team.values().iterator().next());
	}
}