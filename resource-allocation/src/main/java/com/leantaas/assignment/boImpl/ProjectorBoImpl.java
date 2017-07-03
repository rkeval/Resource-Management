package com.leantaas.assignment.boImpl;

import java.util.List;

import com.leantaas.assignment.bo.ProjectorBo;
import com.leantaas.assignment.entity.Projector;
import com.leantaas.assignment.exceptions.ProjectorNotFoundException;
import com.leantaas.assignment.dao.ProjectorDao;
import com.leantaas.assignment.daoImpl.ProjectorDaoImpl;

public class ProjectorBoImpl implements ProjectorBo{

	@Override
	public Projector addProjector(String projectorName) {
		ProjectorDao projectorDao = new ProjectorDaoImpl();
		Projector projector = new Projector(projectorName);
		return projectorDao.addProjector(projector);
	}

	@Override
	public Projector deleteProjector(int id) {
		ProjectorDao projectorDao = new ProjectorDaoImpl();
		return projectorDao.deleteProjector(id);
	}

	@Override
	public Projector getProjector(int id) throws ProjectorNotFoundException {
		ProjectorDao projectorDao = new ProjectorDaoImpl();
		Projector projector = projectorDao.getProjector(id);
		if(projector==null)
			throw new ProjectorNotFoundException(id);
		return projector;
	}

	@Override
	public Projector getProjectorByName(String projectorName) {
		ProjectorDao projectorDao = new ProjectorDaoImpl();
		Projector projector = new Projector(projectorName);
		return projectorDao.getProjectorByName(projector);
	}

	@Override
	public List<Projector> getAllProjector() {
		ProjectorDao projectorDao = new ProjectorDaoImpl();
		return projectorDao.getAllProjector();
	}

}
