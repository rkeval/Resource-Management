package com.leantaas.assignment.bo;

import java.util.Date;
import java.util.List;

import com.leantaas.assignment.entity.Projector;
import com.leantaas.assignment.entity.ProjectorAssignment;
import com.leantaas.assignment.exceptions.ProjectorNotAvailableException;
import com.leantaas.assignment.exceptions.ProjectorNotFoundException;
import com.leantaas.assignment.exceptions.TeamNotFoundException;

public interface ProjectorAssignmentBo {
	ProjectorAssignment requestProjector(String teamName, String startTime, String endTime) throws TeamNotFoundException, ProjectorNotAvailableException ;
	boolean returnProjector(int id);
	boolean cancelProjector(int id);
	List<Projector> checkAvailability(String startTime, String endTime);
	List<ProjectorAssignment> getAllBookedProjectors();
	List<ProjectorAssignment> getLatestAvailable(String startTime) throws ProjectorNotAvailableException;
}
