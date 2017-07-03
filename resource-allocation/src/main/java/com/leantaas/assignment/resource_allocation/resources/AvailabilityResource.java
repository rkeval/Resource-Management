package com.leantaas.assignment.resource_allocation.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.leantaas.assignment.bo.ProjectorAssignmentBo;
import com.leantaas.assignment.entity.Projector;
import com.leantaas.assignment.entity.ProjectorAssignment;
import com.leantaas.assignment.exceptions.ProjectorNotAvailableException;

@Path("/projector-booking/availability/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvailabilityResource {

	@Autowired
	ProjectorAssignmentBo projectorAssignmentBo;
	
	@GET
	@Path("/latest")
	public List<ProjectorAssignment> getLatestAvailable(@QueryParam("startTime") String startTime) throws ProjectorNotAvailableException{
		return projectorAssignmentBo.getLatestAvailable(startTime);
	}
	
	@GET
	@Path("/check")
	public List<Projector> checkAvailability(@QueryParam("startTime") String startTime,
			@QueryParam("endTime") String endTime){
		
		return projectorAssignmentBo.checkAvailability(startTime, endTime);
	}
	
}
