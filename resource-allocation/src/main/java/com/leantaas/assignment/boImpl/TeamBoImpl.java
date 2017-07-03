package com.leantaas.assignment.boImpl;

import java.util.List;

import com.leantaas.assignment.bo.TeamBo;
import com.leantaas.assignment.entity.Team;
import com.leantaas.assignment.exceptions.TeamNotFoundException;
import com.leantaas.assignment.dao.TeamDao;
import com.leantaas.assignment.daoImpl.TeamDaoImpl;
import org.springframework.context.annotation.Bean;



public class TeamBoImpl implements TeamBo{

	@Override
	public Team addTeam(String teamName) {
		TeamDao teamDao = new TeamDaoImpl();
		Team team = new Team(teamName);
		return teamDao.addTeam(team);
	}

	@Override
	public Team getTeam(int id) throws TeamNotFoundException {
		TeamDao teamDao = new TeamDaoImpl();
		Team team = teamDao.getTeam(id);
		if(team == null)
			throw new TeamNotFoundException(id);
		return team;
	}

	@Override
	public Team getTeamByName(String teamName) throws TeamNotFoundException {
		TeamDao teamDao = new TeamDaoImpl();
		Team team = new Team(teamName);
		team = teamDao.getTeamByName(team);
		if(team==null)
			throw new TeamNotFoundException(teamName);
		return team;
	}

	@Override
	public Team deleteTeam(int id) {
		TeamDao teamDao = new TeamDaoImpl();
		return teamDao.deleteTeam(id);
	}

	@Override
	public List<Team> getAllTeams() {
		TeamDao teamDao = new TeamDaoImpl();
		return teamDao.getAllTeams();
	}

}
