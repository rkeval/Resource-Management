package com.leantaas.assignment.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.leantaas.assignment.bo.ProjectorBo;
import com.leantaas.assignment.entity.Projector;
import com.leantaas.assignment.exceptions.ProjectorNotFoundException;

@Controller
public class ProjectorController {
	@Autowired
	ProjectorBo projectorBo;
	@RequestMapping("/addProjector")
	public ModelAndView addProjector(@RequestParam String projectorName ){
		Projector projector = projectorBo.addProjector(projectorName);	
		return getAllProjector();
	}

	@RequestMapping("/deleteProjector")
	public ModelAndView deleteProjector(@RequestParam int projectorId ){
		System.out.println("In delete projector");
		Projector projector = projectorBo.deleteProjector(projectorId);
		return getAllProjector();
	}

	@RequestMapping("/getProjector")
	public ModelAndView getProjector(@RequestParam int projectorId){
		Projector projector;
		try {
			projector = projectorBo.getProjector(projectorId);
		} catch (ProjectorNotFoundException e) {
			return new ModelAndView("errorpage","message",e.getMessage());
		}
		return new ModelAndView("details","projector",projector);
	}

	@RequestMapping("/getProjectorByName")
	public ModelAndView getProjectorByName(@RequestParam String projectorName){
		Projector projector = projectorBo.getProjectorByName(projectorName);
		return new ModelAndView("details","projector",projector);
	}

	@RequestMapping("/details")
	public ModelAndView getAllProjector( ){
		List<Projector> projectors = projectorBo.getAllProjector();
		return new ModelAndView("details","projectors",projectors);
	}

}
