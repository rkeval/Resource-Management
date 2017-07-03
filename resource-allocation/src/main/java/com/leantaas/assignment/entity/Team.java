package com.leantaas.assignment.entity;

public class Team {
	int id;
	String name;
	boolean isActive;
	
	public Team() {
		
	}
	public Team(String teamName) {
		this.name = teamName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
