package com.leantaas.assignment.bo;

import java.util.List;

import com.leantaas.assignment.entity.Projector;
import com.leantaas.assignment.entity.Team;
import com.leantaas.assignment.exceptions.TeamNotFoundException;

public interface TeamBo {
	Team addTeam(String teamName);
	Team getTeamByName(String teamName) throws TeamNotFoundException ;
	Team getTeam(int id) throws TeamNotFoundException;
	Team deleteTeam(int id);
	List<Team> getAllTeams();
	
}
