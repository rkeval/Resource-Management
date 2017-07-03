package com.leantaas.assignment.boImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.leantaas.assignment.bo.ProjectorAssignmentBo;
import com.leantaas.assignment.bo.ProjectorBo;
import com.leantaas.assignment.bo.TeamBo;
import com.leantaas.assignment.dao.ProjectorAssignmentDao;
import com.leantaas.assignment.entity.Projector;
import com.leantaas.assignment.entity.ProjectorAssignment;
import com.leantaas.assignment.entity.Team;
import com.leantaas.assignment.exceptions.ProjectorNotAvailableException;
import com.leantaas.assignment.exceptions.ProjectorNotFoundException;
import com.leantaas.assignment.exceptions.TeamNotFoundException;
import com.leantaas.assignment.utility.ConstantValues;
import com.leantaas.assignment.utility.DateOperations;

public class ProjectorAssignmentBoImpl implements ProjectorAssignmentBo{

	@Autowired
	TeamBo teamBo;
	@Autowired
	ProjectorBo projectorBo;
	@Autowired
	ProjectorAssignmentDao projectorAssignmentDao;

	@Override
	public ProjectorAssignment requestProjector(String teamName, String startTime, String endTime) throws TeamNotFoundException, ProjectorNotAvailableException {
		Date startTimeDate = DateOperations.convertStringToDate(startTime);
		Date endTimeDate = DateOperations.convertStringToDate(endTime);
		Team requesterTeam = teamBo.getTeamByName(teamName);
		ProjectorAssignment projectorAssignment = new ProjectorAssignment();
		projectorAssignment.setRequestedTime(new Date(System.currentTimeMillis()));
		projectorAssignment.setTeam(requesterTeam);
		projectorAssignment.setStartTime(startTimeDate);
		projectorAssignment.setEndTime(endTimeDate);
		Projector projector = projectorAssignmentDao.getAvailableProjector(startTimeDate, endTimeDate);
		if(projector==null){
			throw new ProjectorNotAvailableException();
			/*projectorAssignment.setStatus(ConstantValues.AWAITING);
			return projectorAssignment;*/
		}
		projectorAssignment.setProjector(projector);
		projectorAssignment = projectorAssignmentDao.requestProjector(projectorAssignment);

		return projectorAssignment;
	}

	@Override
	public boolean returnProjector(int id)  {
		return projectorAssignmentDao.returnProjector(id);
	}

	@Override
	public boolean cancelProjector(int id) {
		return projectorAssignmentDao.cancelProjector(id);
	}

	@Override
	public List<Projector> checkAvailability(String startTime, String endTime) {
		Date startTimeDate = DateOperations.convertStringToDate(startTime);
		Date endTimeDate = DateOperations.convertStringToDate(endTime);
		return projectorAssignmentDao.checkAvailability(startTimeDate,endTimeDate);
	}

	@Override
	public List<ProjectorAssignment> getAllBookedProjectors() {
		return projectorAssignmentDao.getAllBookedProjectors();
	}

	@Override
	public List<ProjectorAssignment> getLatestAvailable(String startTime) throws ProjectorNotAvailableException {
		Date startTimeDate = DateOperations.convertStringToDate(startTime);
		List<ProjectorAssignment> assignments = projectorAssignmentDao.getLatestAvailableProjectors(startTimeDate);
		if(assignments.size()==0)					
			throw new ProjectorNotAvailableException();
		return assignments;

	}

}
