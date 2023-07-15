package com.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.jdbc.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.jdbc.model.Circle;

@Component
public class JdbcDaoImpl {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	UserDetails userDetails = null;

	public UserDetails getUserDetails(int userId) {
		Connection conn = null;

		try {

			conn = dataSource.getConnection();

			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM userdetails where userId=?");

			stmt.setInt(1, userId);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				userDetails = new UserDetails(userId, rs.getString("userName"));
			}

			rs.close();
			stmt.close();

			return userDetails;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public int getUserCount() {
		String sql = "SELECT COUNT(*) FROM userdetails";
		jdbcTemplate.setDataSource(dataSource);
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public void insert(UserDetails userDetails) {

		String sql = "INSERT INTO userdetails " + "(userId, userName) VALUES (?, ?)";

		jdbcTemplate = new JdbcTemplate(dataSource);

		jdbcTemplate.update(sql, new Object[] { userDetails.getId(), userDetails.getName() });

	}
	
	public List<UserDetails> getUserDetaiilsObject() {
		String sql = "SELECT * FROM userdetails";

		jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<UserDetails> user = jdbcTemplate.query(
				  sql,
				  (rs, rowNum) -> {
					  UserDetails c = new UserDetails();
					    c.setId(rs.getInt("userId"));
					    c.setName(rs.getString("userName"));
					    return c;
					});
		return user;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
