package com.leantaas.assignment.exceptions;

public class ProjectorNotFoundException extends Exception {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3085089608402469981L;

	public ProjectorNotFoundException(int id) {
		super("Projector with id "+id+ " does not exist.");
	}

}
