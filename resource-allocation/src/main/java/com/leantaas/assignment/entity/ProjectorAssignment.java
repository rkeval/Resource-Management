package com.leantaas.assignment.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProjectorAssignment {
	private int id;
	private Date requestedTime;
	private Date startTime;
	private Date endTime;
	private Team team;
	private Projector projector;
	private String status; //{assigned, cancelled, waiting, returned}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getRequestedTime() {
		return requestedTime;
	}
	public void setRequestedTime(Date requestedTime) {
		this.requestedTime = requestedTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Projector getProjector() {
		return projector;
	}
	public void setProjector(Projector projector) {
		this.projector = projector;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
