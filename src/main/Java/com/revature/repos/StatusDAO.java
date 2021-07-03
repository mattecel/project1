package com.revature.repos;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Status;
import utils.JDBCConnection;

public class StatusDAO implements StatusRepo {
	
	private Connection conn = JDBCConnection.getConnection();
	private ApprovalRepo appDao = new ApprovalDAO();

	@Override
	public void updateStatus(Status st) {
		String sql = "Update statuses set status = ?, priority = ?, status_date = ?, assistant_info = ?, author_info = ?, general_info = ?, senior_info = ? where status_id = ?;";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, st.getStatus());
			ps.setBoolean(2, st.isPriority());
			ps.setString(3, st.getStatusDate());
			ps.setString(4, st.getAssistantInfo());
			ps.setString(5, st.getAuthorInfo());
			ps.setString(6, st.getGeneralInfo());
			ps.setString(7, st.getSeniorInfo());
			ps.setInt(8, st.getStatusId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Status addStatus(Status st, Integer storyId) {
		String sql = "insert into statuses values (DEFAULT, ?, ?, ?, ?, ?, ?, ?) returning *;";
		// id, status, priority, date, assistant info, 

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, st.getStatus());
			ps.setBoolean(2, st.isPriority());
			ps.setString(3, st.getStatusDate());
			ps.setString(4, st.getAssistantInfo());
			ps.setString(5, st.getAuthorInfo());
			ps.setString(6, st.getGeneralInfo());
			ps.setString(7, st.getSeniorInfo());

			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				Status s = new Status();
				s.setStatusId(rs.getInt("status_id"));
				return s;
			}			

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Status getStatusByStory(Integer storyId) {
		String sql = "SELECT * FROM stories LEFT JOIN statuses ON stories.status_id = statuses.status_id WHERE stories.status_id = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, storyId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Status s = new Status();
				s.setStatusId(rs.getInt("status_id"));
				s.setStatus(rs.getString("status"));
				s.setPriority(rs.getBoolean("priority"));
				s.setStatusDate(rs.getString("status_date"));
				s.setAssistantInfo(rs.getString("assistant_info"));
				s.setAuthorInfo(rs.getString("author_info"));
				s.setGeneralInfo(rs.getString("general_info"));
				s.setSeniorInfo(rs.getString("senior_info"));
				
				s.setApproval(appDao.getApprovalByStatus(s.getStatusId()));

				return s;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Status getStatus(Integer statusId) {
		String sql = "SELECT * FROM statuses where status_id = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, statusId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Status s = new Status();
				s.setStatusId(rs.getInt("status_id"));
				s.setStatus(rs.getString("status"));
				s.setPriority(rs.getBoolean("priority"));
				s.setStatusDate(rs.getString("status_date"));
				s.setAssistantInfo(rs.getString("assistant_info"));
				s.setAuthorInfo(rs.getString("author_info"));
				s.setGeneralInfo(rs.getString("general_info"));
				s.setSeniorInfo(rs.getString("senior_info"));
				
				s.setApproval(appDao.getApprovalByStatus(s.getStatusId()));

				return s;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
