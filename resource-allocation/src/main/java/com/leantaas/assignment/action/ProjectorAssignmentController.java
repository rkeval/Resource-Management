package com.leantaas.assignment.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.leantaas.assignment.bo.ProjectorAssignmentBo;
import com.leantaas.assignment.entity.Projector;
import com.leantaas.assignment.entity.ProjectorAssignment;
import com.leantaas.assignment.exceptions.ProjectorNotAvailableException;
import com.leantaas.assignment.exceptions.ProjectorNotFoundException;
import com.leantaas.assignment.exceptions.TeamNotFoundException;
import com.leantaas.assignment.utility.ConstantValues;

@Controller
public class ProjectorAssignmentController {
	@Autowired
	ProjectorAssignmentBo assignmentBo;


	@RequestMapping("/requestProjector")
	public ModelAndView requestProjector(@RequestParam String teamName, 
			@RequestParam  String startTime,
			@RequestParam String endTime){

		ProjectorAssignment projectorAssignment=null;
		try {
			projectorAssignment = assignmentBo.requestProjector(teamName, startTime, endTime);
		} catch (TeamNotFoundException e) {
			return new ModelAndView("errorpage","message",e.getMessage());
		} catch (ProjectorNotAvailableException e) {
			return getLatestAvailable(startTime);
		}
		return new ModelAndView("requestresult","projectorAssignmnet",projectorAssignment);
	}

	@RequestMapping("/getLatestAvailable")
	public ModelAndView getLatestAvailable(@RequestParam String startTime) {
		List<ProjectorAssignment> assignments;
		try {
			assignments = assignmentBo.getLatestAvailable(startTime);
		} catch (ProjectorNotAvailableException e) {
			return new ModelAndView("errorpage","message",e.getMessage());
		}

		return new ModelAndView("timesuggestion","assignments",assignments);
	}

	@RequestMapping("/cancelProjectorRequest")
	public ModelAndView cancelProjectorRequest(@RequestParam int id){
		boolean result;
		result = assignmentBo.cancelProjector(id);
		return getAllBookedProjectors();
	}

	@RequestMapping("/returnProjector")
	public ModelAndView returnProjector(@RequestParam int id){
		boolean result;
		result = assignmentBo.returnProjector(id);
		return getAllBookedProjectors();
	}

	@RequestMapping("/checkProjectorAvailability")
	public ModelAndView checkProjectorAvailability(@RequestParam String startTime,
			@RequestParam String endTime){	
		List<Projector> projectors =assignmentBo.checkAvailability(startTime, endTime);
		return new ModelAndView("availability", "projectors", projectors);
	}

	@RequestMapping("/getBookedSlots")
	public ModelAndView getAllBookedProjectors(){
		List<ProjectorAssignment> assignments = assignmentBo.getAllBookedProjectors();
		return new ModelAndView("bookedslots", "assignments", assignments);
	}

}
