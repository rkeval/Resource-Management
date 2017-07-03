package com.leantaas.assignment.resource_allocation.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import com.leantaas.assignment.bo.ProjectorAssignmentBo;
import com.leantaas.assignment.entity.ParseBoolean;
import com.leantaas.assignment.entity.ProjectorAssignment;
import com.leantaas.assignment.exceptions.ProjectorNotAvailableException;
import com.leantaas.assignment.exceptions.TeamNotFoundException;

@Path("/projector-booking")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectorAssignmentResource {
	@Autowired
	ProjectorAssignmentBo projectorAssignmentBo;

	/**
	 * @param teamName
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws TeamNotFoundException
	 * @throws ProjectorNotAvailableException
	 */
	@GET
	public ProjectorAssignment bookProjector(@QueryParam("teamName") String teamName,
			@QueryParam("startTime") String startTime, @QueryParam("endTime") String endTime)
			throws TeamNotFoundException, ProjectorNotAvailableException {
		ProjectorAssignment projectorAssignment = projectorAssignmentBo.requestProjector(teamName, startTime, endTime);
		return projectorAssignment;
	}

	@PUT
	@Path("/{id}")
	public ParseBoolean returnProjector(@PathParam("id") int id) {
		boolean result = projectorAssignmentBo.returnProjector(id);
		ParseBoolean parseBoolean = new ParseBoolean(result);
		return parseBoolean;
	}

	@DELETE
	@Path("/{id}")
	public ParseBoolean cancelProjector(@PathParam("id") int id) {
		boolean result = projectorAssignmentBo.cancelProjector(id);
		ParseBoolean parseBoolean = new ParseBoolean(result);
		return parseBoolean;
	}
}
