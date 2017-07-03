package com.leantaas.assignment.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.leantaas.assignment.database.DatabaseConnection;
import com.leantaas.assignment.entity.Team;
import com.leantaas.assignment.dao.TeamDao;

public class TeamDaoImpl implements TeamDao{

	@Override
	public Team addTeam(Team team) {
		Connection con = DatabaseConnection.getConnection();
		String sqlQuery = "INSERT INTO `"+DatabaseConnection.getDB_NAME()+"`.`TEAM` (`NAME`,`IS_ACTIVE`) VALUES (?,?)";
		try {
			PreparedStatement pstmt = con.prepareStatement(sqlQuery);
			pstmt.setString(1, team.getName());
			pstmt.setString(2, Boolean.toString(true));
			int teamAdd = pstmt.executeUpdate();
			System.out.println("team added successfull " + teamAdd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return getTeamByName(team);
	}

	@Override
	public Team getTeam(int id) {
		Team team=null;
		Connection connection = DatabaseConnection.getConnection();

		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement("SELECT * FROM `"+DatabaseConnection.getDB_NAME()+"`.`TEAM` where `ID` = ? and IS_ACTIVE='true'");
			pstmt.setInt(1, id);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				team = new Team();
				team.setId(resultSet.getInt("ID"));
				team.setName(resultSet.getString("NAME"));
				team.setActive(Boolean.parseBoolean(resultSet.getString("IS_ACTIVE")));
				return team;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return team;
	}

	@Override
	public Team deleteTeam(int id) {
		int teamUpdate = 0;
		Team team = getTeam(id);
		if(team==null)
			return null;
		try {
			System.out.println("in deleteTeam daoImpl");
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("UPDATE `"+DatabaseConnection.getDB_NAME()+"`.`TEAM` SET `IS_ACTIVE`='false' WHERE `ID`="
							+ id);
			teamUpdate = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("Failed to update status" + e);
			e.printStackTrace();
		}
		return team;
	}

	@Override
	public Team getTeamByName(Team team) {
		Connection connection = DatabaseConnection.getConnection();
		Team resultTeam=null;
		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement("SELECT * FROM `"+DatabaseConnection.getDB_NAME()+"`.`TEAM` where `NAME` = ? and IS_ACTIVE='true'");

			pstmt.setString(1, team.getName());
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				resultTeam = new Team();
				resultTeam.setId(resultSet.getInt("ID"));
				resultTeam.setName(resultSet.getString("NAME"));
				resultTeam.setActive(Boolean.parseBoolean(resultSet.getString("IS_ACTIVE")));
				return resultTeam;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultTeam;
	}

	@Override
	public List<Team> getAllTeams() {
		List<Team> teams=new ArrayList<>();
		Connection connection = DatabaseConnection.getConnection();

		PreparedStatement pstmt;
		try {
			pstmt = connection.prepareStatement("SELECT * FROM `"+DatabaseConnection.getDB_NAME()+"`.`TEAM` where IS_ACTIVE='true'");
			//pstmt.setInt(1, id);
			ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				Team team = new Team();
				team.setId(resultSet.getInt("ID"));
				team.setName(resultSet.getString("NAME"));
				team.setActive(Boolean.parseBoolean(resultSet.getString("IS_ACTIVE")));
				teams.add(team);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teams;
	}

}
