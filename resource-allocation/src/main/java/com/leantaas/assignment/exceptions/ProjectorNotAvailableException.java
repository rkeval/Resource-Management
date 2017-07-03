package com.leantaas.assignment.exceptions;

public class ProjectorNotAvailableException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3355434683680172656L;

	public ProjectorNotAvailableException() {
		super("Sorry, Your request can not be processed"
				+ " as no projector available in your required time."
				+ " Please try with other timings.");
	}
}
