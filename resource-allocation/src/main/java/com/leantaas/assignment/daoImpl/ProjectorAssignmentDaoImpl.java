package com.leantaas.assignment.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.leantaas.assignment.bo.ProjectorBo;
import com.leantaas.assignment.bo.TeamBo;
import com.leantaas.assignment.dao.ProjectorAssignmentDao;
import com.leantaas.assignment.database.DatabaseConnection;
import com.leantaas.assignment.entity.Projector;
import com.leantaas.assignment.entity.ProjectorAssignment;
import com.leantaas.assignment.entity.Team;
import com.leantaas.assignment.exceptions.ProjectorNotFoundException;
import com.leantaas.assignment.exceptions.TeamNotFoundException;
import com.leantaas.assignment.utility.ConstantValues;

public class ProjectorAssignmentDaoImpl implements ProjectorAssignmentDao{
	Connection con = DatabaseConnection.getConnection();
	@Autowired
	TeamBo teamBo;
	@Autowired
	ProjectorBo projectorBo;
	@Override
	public ProjectorAssignment requestProjector(ProjectorAssignment projectorAssignment) {
		String sqlQuery = "INSERT INTO `"+DatabaseConnection.getDB_NAME()+"`.`PROJECTOR_ASSIGNMENT` "
				+ "(`teamId`,`projectorId`,`requestedtime`,`starttime`,`endtime`,`status`) "
				+ "values(?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);

			System.out.println(projectorAssignment.getTeam().getId());
			pstmt.setInt(1, projectorAssignment.getTeam().getId());
			pstmt.setInt(2, projectorAssignment.getProjector().getId());
			pstmt.setTimestamp(3, new java.sql.Timestamp(projectorAssignment.getRequestedTime().getTime()));
			pstmt.setTimestamp(4, new java.sql.Timestamp(projectorAssignment.getStartTime().getTime()));
			pstmt.setTimestamp(5, new java.sql.Timestamp(projectorAssignment.getEndTime().getTime()));
			pstmt.setString(6, ConstantValues.ASSIGNED);
			System.out.println(pstmt);
			int assigned = pstmt.executeUpdate();
			if(assigned==1)
				projectorAssignment.setStatus(ConstantValues.ASSIGNED);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return projectorAssignment;
	}

	@Override
	public Projector getAvailableProjector(Date startTime, Date endTime ){
		String sqlQuery = "SELECT * "
				+ "FROM "+DatabaseConnection.getDB_NAME()+".PROJECTOR"
				+ " WHERE IS_ACTIVE='true' AND ID NOT IN (SELECT DISTINCT (PA.PROJECTORID) "
				+ "FROM "+DatabaseConnection.getDB_NAME()+".PROJECTOR_ASSIGNMENT PA "
				+ "WHERE PA.STATUS='"+ConstantValues.ASSIGNED+"' "
				+ "AND (PA.STARTTIME<? AND PA.ENDTIME>?))"
				+ " LIMIT 1";	
		PreparedStatement pstmt;
		Projector projector = null; 
		try {
			pstmt = con.prepareStatement(sqlQuery);
			pstmt.setTimestamp(1, new java.sql.Timestamp(endTime.getTime()));
			pstmt.setTimestamp(2, new java.sql.Timestamp(startTime.getTime()));
			System.out.println(pstmt);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				projector = new Projector();
				projector.setId(resultSet.getInt("ID"));
				projector.setName(resultSet.getString("NAME"));
				projector.setActive(Boolean.parseBoolean(resultSet.getString("IS_ACTIVE")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projector;

	}

	@Override
	public boolean returnProjector(int id) {
		String sqlQuery = "Update "+DatabaseConnection.getDB_NAME()+".PROJECTOR_ASSIGNMENT PA  "
				+ "SET `status`=?"
				+ " WHERE id = ? AND status = ?";
		try {
			PreparedStatement pstmt = con
					.prepareStatement(sqlQuery);
			pstmt.setString(1, ConstantValues.RETURNED);
			pstmt.setInt(2, id);
			pstmt.setString(3, ConstantValues.ASSIGNED);
			System.out.println(pstmt);
			int returned = pstmt.executeUpdate();
			if(returned>0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean cancelProjector(int id) {
		String sqlQuery = "Update "+DatabaseConnection.getDB_NAME()+".PROJECTOR_ASSIGNMENT PA  "
				+ "SET `status`=?"
				+ " WHERE id = ? AND status = ?";
		try {
			PreparedStatement pstmt = con
					.prepareStatement(sqlQuery);
			pstmt.setString(1, ConstantValues.CANCELLED);
			pstmt.setInt(2, id);
			pstmt.setString(3, ConstantValues.ASSIGNED);
			int cancelled = pstmt.executeUpdate();
			if(cancelled>0)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Projector> checkAvailability(Date startTime, Date endTime) {
		String sqlQuery = "SELECT * "
				+ "FROM "+DatabaseConnection.getDB_NAME()+".PROJECTOR "
				+ "WHERE IS_ACTIVE='true' AND ID NOT IN (SELECT DISTINCT (PA.PROJECTORID) "
				+ "FROM "+DatabaseConnection.getDB_NAME()+".PROJECTOR_ASSIGNMENT PA "
				+ "WHERE PA.STATUS='"+ConstantValues.ASSIGNED+"' "
				+ "AND (PA.STARTTIME<? AND PA.ENDTIME>?))";	
		PreparedStatement pstmt;
		List<Projector> projectors = new ArrayList<Projector>(); 
		try {
			pstmt = con.prepareStatement(sqlQuery);
			pstmt.setTimestamp(1, new java.sql.Timestamp(endTime.getTime()));
			pstmt.setTimestamp(2, new java.sql.Timestamp(startTime.getTime()));
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Projector projector = new Projector();
				projector.setId(resultSet.getInt("ID"));
				projector.setName(resultSet.getString("NAME"));
				projector.setActive(Boolean.parseBoolean(resultSet.getString("IS_ACTIVE")));
				projectors.add(projector);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectors;
	}

	@Override
	public List<ProjectorAssignment> getAllBookedProjectors() {
		List<ProjectorAssignment> assignments = new ArrayList<>();
		String sqlQuery = "SELECT * "
				+ "FROM `"+DatabaseConnection.getDB_NAME()+"`.`PROJECTOR_ASSIGNMENT` "
				+ "WHERE status='"+ConstantValues.ASSIGNED+"'";

		try {
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				ProjectorAssignment projectorAssignment = new ProjectorAssignment();
				try {
					projectorAssignment.setId(resultSet.getInt("ID"));
					projectorAssignment.setStatus(resultSet.getString("status"));
					projectorAssignment.setRequestedTime(resultSet.getTimestamp("requestedtime"));
					projectorAssignment.setStartTime(resultSet.getTimestamp("starttime"));
					projectorAssignment.setEndTime(resultSet.getTimestamp("endtime"));
					projectorAssignment.setTeam(teamBo.getTeam(resultSet.getInt("teamId")));
					projectorAssignment.setProjector(projectorBo.getProjector(resultSet.getInt("projectorId")));
					assignments.add(projectorAssignment);
				} catch (TeamNotFoundException | ProjectorNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return assignments;
	}

	@Override
	public List<ProjectorAssignment> getLatestAvailableProjectors(Date startTimeDate) {
		List<ProjectorAssignment> assignments = new ArrayList<>();
		String sqlQuery = "SELECT PA1.PROJECTORID, min(PA1.ENDTIME) et "
				+ "FROM "+DatabaseConnection.getDB_NAME()+".PROJECTOR_ASSIGNMENT PA1 "
				+ " JOIN "+DatabaseConnection.getDB_NAME()+".PROJECTOR PR ON (PA1.PROJECTORID=PR.ID AND IS_ACTIVE='true')"
				+ "WHERE PA1.ENDTIME>? AND PA1.ENDTIME NOT IN (SELECT PA2.STARTTIME "
				+ "FROM "+DatabaseConnection.getDB_NAME()+".PROJECTOR_ASSIGNMENT PA2 "
				+ " WHERE PA1.PROJECTORID=PA2.PROJECTORID) "
				+ "GROUP BY PA1.PROJECTORID "
				+ "ORDER BY et";
		try {
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
			pstmt.setTimestamp(1, new java.sql.Timestamp(startTimeDate.getTime()));
			System.out.println(pstmt);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				ProjectorAssignment projectorAssignment = new ProjectorAssignment();
				try {
					projectorAssignment.setStartTime(resultSet.getTimestamp("et"));
					projectorAssignment.setProjector(projectorBo.getProjector(resultSet.getInt("projectorId")));
					assignments.add(projectorAssignment);
				} catch (ProjectorNotFoundException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
System.out.println(assignments);
		return assignments;
	}
}
