package com.leantaas.assignment.dao;

import java.util.List;

import com.leantaas.assignment.entity.Projector;
import com.leantaas.assignment.entity.Team;

public interface ProjectorDao {
	Projector addProjector(Projector projector);
	Projector getProjector(int id);
	Projector deleteProjector(int id);
	/*boolean issueProjector(Projector projector, Team team);
	boolean returnProjector(Projector projector, Team team);*/
	Projector getProjectorByName(Projector projector);
	List<Projector> getAllProjector();
}
