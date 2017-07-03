package com.leantaas.assignment.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.leantaas.assignment.database.DatabaseConnection;
import com.leantaas.assignment.entity.Projector;
import com.leantaas.assignment.dao.ProjectorDao;

public class ProjectorDaoImpl implements ProjectorDao{
	
/*	public static void main(String[] args) {
		ProjectorDaoImpl daoImpl = new ProjectorDaoImpl();
		Projector proj = new Projector("Projector1");
				daoImpl.addProjector(proj);
	}*/
	@Override
	public Projector addProjector(Projector projector) {
		Connection con = DatabaseConnection.getConnection();
		String sqlQuery = "INSERT INTO `"+DatabaseConnection.getDB_NAME()+"`.`PROJECTOR` (`NAME`,`IS_ACTIVE`) VALUES (?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
			pstmt.setString(1, projector.getName());
			pstmt.setString(2, Boolean.toString(true));
			int projectorAdd = pstmt.executeUpdate();
			System.out.println("projector Add Successfull" + projectorAdd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getProjectorByName(projector);
	}

	@Override
	public Projector getProjector(int id) {
		Projector projector=null;
		Connection connection = DatabaseConnection.getConnection();
		
			PreparedStatement pstmt;
			try {
				pstmt = connection.prepareStatement("SELECT * FROM `"+DatabaseConnection.getDB_NAME()+"`.`PROJECTOR` where `ID` = ? and IS_ACTIVE='true'");
			
			pstmt.setInt(1, id);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
					projector = new Projector();
					projector.setId(resultSet.getInt("ID"));
					projector.setName(resultSet.getString("NAME"));
					projector.setActive(Boolean.parseBoolean(resultSet.getString("IS_ACTIVE")));
					return projector;
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return projector;
	}
	
	@Override
	public Projector getProjectorByName(Projector projector) {
		//Projector projector=null;
		Connection connection = DatabaseConnection.getConnection();
		
			PreparedStatement pstmt;
			try {
				pstmt = connection.prepareStatement("SELECT * FROM `"+DatabaseConnection.getDB_NAME()+"`.`PROJECTOR` where `NAME` = ? and IS_ACTIVE='true'");
			
			pstmt.setString(1, projector.getName());
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
					projector = new Projector();
					projector.setId(resultSet.getInt("ID"));
					projector.setName(resultSet.getString("NAME"));
					projector.setActive(Boolean.parseBoolean(resultSet.getString("IS_ACTIVE")));
					return projector;
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return projector;
	}

	@Override
	public Projector deleteProjector(int id) {
		int prjectorUpdate = 0;
		Projector projector = getProjector(id);
		if(projector==null)
			return null;
		try {
			System.out.println("in deleteProjector daoImpl");
			Connection connection = DatabaseConnection.getConnection();
			
			PreparedStatement pstmt = connection
					.prepareStatement("UPDATE `"+DatabaseConnection.getDB_NAME()+"`.`PROJECTOR` SET `IS_ACTIVE`='false' WHERE `ID`="
							+ id);
			prjectorUpdate = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Failed to update status" + e);
			e.printStackTrace();
		}
		return projector;
	}

	@Override
	public List<Projector> getAllProjector() {
		Connection connection = DatabaseConnection.getConnection();
		List<Projector> projectors = new ArrayList<>();
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement("SELECT * FROM `"+DatabaseConnection.getDB_NAME()+"`.`PROJECTOR` where IS_ACTIVE='true'");
		
		ResultSet resultSet = pstmt.executeQuery();
		while (resultSet.next()) {
				Projector projector = new Projector();
				projector.setId(resultSet.getInt("ID"));
				projector.setName(resultSet.getString("NAME"));
				projector.setActive(Boolean.parseBoolean(resultSet.getString("IS_ACTIVE")));
				projectors.add( projector);
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return projectors;
	}
}
