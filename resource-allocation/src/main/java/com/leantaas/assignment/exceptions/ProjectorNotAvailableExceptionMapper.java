package com.leantaas.assignment.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.leantaas.assignment.entity.ErrorMeassage;

@Provider
public class ProjectorNotAvailableExceptionMapper implements ExceptionMapper<ProjectorNotAvailableException>{

	@Override
	public Response toResponse(ProjectorNotAvailableException ex) {
		ErrorMeassage errorMeassage= new ErrorMeassage(ex.getMessage(),Status.PRECONDITION_FAILED.getStatusCode(),"Insufficient Resources.");
		return Response.status(Status.PRECONDITION_FAILED)
				.entity(errorMeassage)
				.build();
	}
	

}
