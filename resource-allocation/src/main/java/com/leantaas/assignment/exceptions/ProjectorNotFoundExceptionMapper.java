package com.leantaas.assignment.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.leantaas.assignment.entity.ErrorMeassage;

@Provider
public class ProjectorNotFoundExceptionMapper implements ExceptionMapper<ProjectorNotFoundException>{
	
	@Override
	public Response toResponse(ProjectorNotFoundException ex) {
		ErrorMeassage errorMeassage= new ErrorMeassage(ex.getMessage(),Status.NOT_FOUND.getStatusCode(),"Invalid Data Input.");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMeassage)
				.build();
	}
	

}
