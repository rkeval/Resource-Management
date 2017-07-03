package com.leantaas.assignment.resource_allocation.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import com.leantaas.assignment.bo.ProjectorAssignmentBo;
import com.leantaas.assignment.entity.*;

@Path("/projector-booking/history")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HistoryResource {

	@Autowired
	private ProjectorAssignmentBo projectorAssignmentBo;

	@GET
	public List<ProjectorAssignment> getAllBookedProjectors() {
		System.out.println("working kya");
		if (projectorAssignmentBo == null) {
			System.out.println("Bo method not initilized");
			return null;
		}
		return projectorAssignmentBo.getAllBookedProjectors();
	}

}
