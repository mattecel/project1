package com.revature.repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Author;

import utils.JDBCConnection;

public class AuthorDAO implements AuthorRepo {

	private Connection conn = JDBCConnection.getConnection();
	private StoryRepo stoDao = new StoryDAO();

	@Override
	public void addAuthor(Author a) {
		// If i get around to registering new users;

	}

	@Override
	public void updateAuthorPoints(Author a) {
		String sql = "update authors set author_points = ? where author_id = ?;";
		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, a.getAuthorPoints());
			ps.setInt(2, a.getAuthorId());

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public Author getAuthor(String user, String pass) { // passed username and password
		String sql = "select * from authors where author_username = ? and author_password = ?";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Author a = new Author();
				a.setAuthorId(rs.getInt("author_id"));
				a.setAuthorUsername(user);
				a.setAuthorPassword(pass);
				a.setAuthorFirstName(rs.getString("author_first_name"));
				a.setAuthorLastName(rs.getString("author_last_name"));
				a.setAuthorPoints(rs.getInt("author_points"));

				a.setStories(stoDao.getStories(a.getAuthorId()));
				return a;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Author getAuthor(Integer id) {
		String sql = "select * from authors where author_id = ?";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Author a = new Author();
				a.setAuthorId(rs.getInt("author_id"));
				a.setAuthorUsername(rs.getString("author_username"));
				a.setAuthorPassword(rs.getString("author_password"));
				a.setAuthorFirstName(rs.getString("author_first_name"));
				a.setAuthorLastName(rs.getString("author_last_name"));
				a.setAuthorPoints(rs.getInt("author_points"));

				a.setStories(stoDao.getStories(a.getAuthorId()));
				return a;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Author getAuthorByStory(Integer id) {
		String sql = "SELECT * FROM stories s LEFT JOIN authors a ON s.author_id = a.author_id WHERE story_id = ?;";

		try {

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Author a = new Author();
				a.setAuthorId(rs.getInt("author_id"));
				a.setAuthorUsername(rs.getString("author_username"));
				a.setAuthorPassword(rs.getString("author_password"));
				a.setAuthorFirstName(rs.getString("author_first_name"));
				a.setAuthorLastName(rs.getString("author_last_name"));
				a.setAuthorPoints(rs.getInt("author_points"));

				a.setStories(stoDao.getStories(a.getAuthorId()));
				return a;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
