package com.leantaas.assignment.dao;

import java.util.Date;
import java.util.List;

import com.leantaas.assignment.entity.Projector;
import com.leantaas.assignment.entity.ProjectorAssignment;
import com.leantaas.assignment.entity.Team;

public interface ProjectorAssignmentDao {

	ProjectorAssignment requestProjector(ProjectorAssignment projectorAssignment);

	boolean returnProjector(int id);

	boolean cancelProjector(int id);

	List<Projector> checkAvailability(Date startTime, Date endTime);

	Projector getAvailableProjector(Date startTime, Date endTime);

	List<ProjectorAssignment> getAllBookedProjectors();

	List<ProjectorAssignment> getLatestAvailableProjectors(Date startTimeDate);

}
