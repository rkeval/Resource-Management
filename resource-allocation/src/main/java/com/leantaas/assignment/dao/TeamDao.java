package com.leantaas.assignment.dao;

import java.util.List;

import com.leantaas.assignment.entity.Projector;
import com.leantaas.assignment.entity.Team;

public interface TeamDao {
	Team addTeam(Team team);
	Team getTeam(int id);
	Team deleteTeam(int id);
	Team getTeamByName(Team team);
	List<Team> getAllTeams();
}
