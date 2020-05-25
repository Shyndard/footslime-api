package com.shyndard.util.footslime.api.entity;

import java.util.Date;
import java.util.UUID;

public class Match {

	private UUID id;
	private Date startAt;
	private Date endAt;
	private int blueTeamPoint;
	private int redTeamPoint;
	
	public Match(Date startAt) {
		this.id = UUID.randomUUID();
		this.startAt = startAt;
	}

	public UUID getId() {
		return id;
	}
	
	public Match setId(UUID id) {
		this.id = id;
		return this;
	}
	
	public Date getStartAt() {
		return startAt;
	}
	
	public Match setStartAt(Date startAt) {
		this.startAt = startAt;
		return this;
	}
	
	public Date getEndAt() {
		return endAt;
	}
	
	public Match setEndAt(Date endAt) {
		this.endAt = endAt;
		return this;
	}
	
	public int getBlueTeamPoint() {
		return blueTeamPoint;
	}
	
	public Match setBlueTeamPoint(int blueTeamPoint) {
		this.blueTeamPoint = blueTeamPoint;
		return this;
	}
	
	public int getRedTeamPoint() {
		return redTeamPoint;
	}
	
	public Match setRedTeamPoint(int redTeamPoint) {
		this.redTeamPoint = redTeamPoint;
		return this;
	}

	public Match addPointToTeam(String team) {
		if("red".equalsIgnoreCase(team)) {
			this.redTeamPoint++;
		} else if("blue".equalsIgnoreCase(team)) {
			this.blueTeamPoint++;
		}
		return this;
	}
	
}
