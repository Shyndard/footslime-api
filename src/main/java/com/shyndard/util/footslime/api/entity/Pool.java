package com.shyndard.util.footslime.api.entity;

import java.util.List;
import java.util.UUID;

public class Pool {

	private UUID id;
	private String name;
	private List<Team> teams;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

}
