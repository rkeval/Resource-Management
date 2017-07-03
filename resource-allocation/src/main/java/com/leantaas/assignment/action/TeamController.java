package com.leantaas.assignment.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.leantaas.assignment.bo.ProjectorBo;
import com.leantaas.assignment.bo.TeamBo;
import com.leantaas.assignment.entity.Team;
import com.leantaas.assignment.exceptions.TeamNotFoundException;

@Controller
public class TeamController {
	@Autowired
	TeamBo teamBo ;
	@RequestMapping("/addTeam")
	public ModelAndView addTeam(@RequestParam String teamName ){

		Team team = teamBo.addTeam(teamName);	
		return getAllTeams();
	}

	@RequestMapping("/getTeam")
	public ModelAndView getTeam(@RequestParam int teamId){
		Team team;
		try {
			team = teamBo.getTeam(teamId);
		} catch (TeamNotFoundException e) {
			return new ModelAndView("errorpage","message",e.getMessage());
		}	
		return new ModelAndView("teamdetails","team",team);
	}

	@RequestMapping("/getTeamByName")
	public ModelAndView getTeamByName(@RequestParam String teamName ){
		Team team;
		try {
			team = teamBo.getTeamByName(teamName);
		} catch (TeamNotFoundException e) {
			return new ModelAndView("errorpage","message",e.getMessage());
		}	
		return new ModelAndView("teamdetails","team",team);
	}

	@RequestMapping("/deleteTeam")
	public ModelAndView deleteTeam(@RequestParam int teamId ){
		Team team = teamBo.deleteTeam(teamId);	
		return getAllTeams();
	}
	@RequestMapping("/teamlist")
	public ModelAndView getAllTeams(){
		List<Team> teams = teamBo.getAllTeams();
		return new ModelAndView("teamlist","teams",teams);
	}
}
