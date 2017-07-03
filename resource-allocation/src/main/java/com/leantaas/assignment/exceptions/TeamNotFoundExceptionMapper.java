package com.leantaas.assignment.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.leantaas.assignment.entity.ErrorMeassage;


@Provider
public class TeamNotFoundExceptionMapper implements ExceptionMapper<TeamNotFoundException>{

	@Override
	public Response toResponse(TeamNotFoundException ex) {
		System.out.println("Inside TeamNotFoundExceptionMapper:");
		ErrorMeassage errorMeassage= new ErrorMeassage(ex.getMessage(),Status.NOT_FOUND.getStatusCode(),"Invalid Data Input.");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMeassage)
				.build();
	
	}
	

}
