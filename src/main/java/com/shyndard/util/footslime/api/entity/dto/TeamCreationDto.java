package com.shyndard.util.footslime.api.entity.dto;

import java.util.List;

public class TeamCreationDto {

	private String name;
	private List<String> usernames;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getUsernames() {
		return usernames;
	}

	public void setUsernames(List<String> usernames) {
		this.usernames = usernames;
	}

}
