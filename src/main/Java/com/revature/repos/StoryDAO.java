package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Approval;
import com.revature.beans.Status;
import com.revature.beans.Story;

import utils.JDBCConnection;

public class StoryDAO implements StoryRepo {

	private Connection conn = JDBCConnection.getConnection();
	private StatusRepo staDao = new StatusDAO();
	private ApprovalRepo appDao = new ApprovalDAO();

	@Override
	public Story addStory(Story story, Integer id) {
		// story_id, title, tagline, description, completion_date, author_id, genre_id,
		// weight_id, status_id
		
		String sql = "insert into stories values (DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?) returning *;";
		
		String sGen = story.getGenre();
		String sWei = story.getWeight();
		Status stat = story.getStatus();
		Approval aApp = stat.getApproval();

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, story.getTitle());
			ps.setString(2, story.getTagline());
			ps.setString(3, story.getDescription());
			ps.setString(4, story.getCompletionDate());
			ps.setInt(5, id);

			// -----------Genre----------

			if (sGen.equals("Mystery")) {
				ps.setInt(6, 1);
			}
			if (sGen.equals("Fantasy")) {
				ps.setInt(6, 2);
			}
			if (sGen.equals("Horror")) {
				ps.setInt(6, 3);
			}
			if (sGen.equals("Romance")) {
				ps.setInt(6, 4);
			}
			if (sGen.equals("Thriller")) {
				ps.setInt(6, 5);
			}
			if (sGen.equals("Biography")) {
				ps.setInt(6, 6);
			}

			// -------------Weight--------------

			if (sWei.equals("Novel")) {
				ps.setInt(7, 1);
			}
			if (sWei.equals("Novella")) {
				ps.setInt(7, 2);
			}
			if (sWei.equals("Short Story")) {
				ps.setInt(7, 3);
			}
			if (sWei.equals("Article")) {
				ps.setInt(7, 4);
			}

			ps.setInt(8, 1);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer aStoryId = rs.getInt("story_id");
				System.out.println(aStoryId);
				Status statId = staDao.addStatus(stat, aStoryId);
				Integer astatId = statId.getStatusId();
				updateStoryStatus(astatId, aStoryId);
				appDao.addApproval(aApp, astatId);
				return getStoryById(aStoryId);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public void updateStoryStatus(Integer statid, Integer storyid) {
		String sql = "update stories set status_id = ? where story_id = ?;";
		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, statid);
			ps.setInt(2, storyid);

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateStory(Story story) {
		String sql = "update stories set title = ?, tagline = ?, description = ?, completion_date = ?;";
		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, story.getTitle());
			ps.setString(2, story.getTagline());
			ps.setString(3, story.getDescription());
			ps.setString(4, story.getCompletionDate());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Story> getStories(Integer id) {
		List<Story> stories = new ArrayList<Story>();
		String sql = "SELECT * FROM stories s LEFT JOIN genres g ON s.genre_id = g.genre_id LEFT JOIN weights w ON w.weight_id = s.weight_id WHERE s.author_id = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Story s = new Story();
				s.setStoryId(rs.getInt("story_id"));
				s.setTitle(rs.getString("title"));
				s.setTagline(rs.getString("tagline"));
				s.setDescription(rs.getString("description"));
				s.setCompletionDate(rs.getString("completion_date"));
				s.setGenre(rs.getString("genre"));
				s.setWeight(rs.getString("weight"));

				s.setStatus(staDao.getStatusByStory(s.getStoryId()));

				stories.add(s);
			}
			return stories;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Story getStoryById(Integer id) {
		String sql = "SELECT * FROM stories s LEFT JOIN genres g ON s.genre_id = g.genre_id LEFT JOIN weights w ON w.weight_id = s.weight_id WHERE s.story_id = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Story s = new Story();
				s.setStoryId(rs.getInt("story_id"));
				s.setTitle(rs.getString("title"));
				s.setTagline(rs.getString("tagline"));
				s.setDescription(rs.getString("description"));
				s.setCompletionDate(rs.getString("completion_date"));
				s.setGenre(rs.getString("genre"));
				s.setWeight(rs.getString("weight"));

				s.setStatus(staDao.getStatusByStory(s.getStoryId()));

				return s;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
