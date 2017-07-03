package com.leantaas.assignment.entity;

import java.util.ArrayList;
import java.util.Stack;



public class Projector {
	private int id;
	private String name;
	private boolean active;
	
	public Projector() {
	
	}
	public Projector(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
		
	public void setId(int id) {
		this.id = id;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}

	
}
