package com.shyndard.util.footslime.api.entity;

import java.util.List;
import java.util.UUID;

public class Team {

	private UUID id;
	private String name;
	private List<Player> players;

	public Team(String name) {
		id = UUID.randomUUID();
		this.name = name;
	}

	public Team() {
		// TODO Auto-generated constructor stub
	}

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

	public List<Player> getPlayers() {
		return players;
	}

	public Team setPlayers(List<Player> players) {
		this.players = players;
		return this;
	}

}
