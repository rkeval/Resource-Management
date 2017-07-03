package com.leantaas.assignment.bo;

import java.util.List;

import com.leantaas.assignment.entity.Projector;
import com.leantaas.assignment.exceptions.ProjectorNotFoundException;

public interface ProjectorBo {
	Projector addProjector(String projectorName);
	Projector deleteProjector(int id);
	Projector getProjector(int id) throws ProjectorNotFoundException;
	Projector getProjectorByName(String projectorName);
	List<Projector> getAllProjector();
}
