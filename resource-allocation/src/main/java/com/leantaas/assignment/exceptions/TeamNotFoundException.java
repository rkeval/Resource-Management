package com.leantaas.assignment.exceptions;

public class TeamNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3146128460428117603L;

	public TeamNotFoundException(int teamId) {
		super("Team with id "+teamId+ " does not exist.");
	}

	public TeamNotFoundException(String teamName) {
		super("Team "+teamName+ " does not exist.");
	}

}
