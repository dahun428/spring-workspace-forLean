package kr.co.jhta.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import kr.co.jhta.vo.User;

public class UserDaoSpringJdbcImpl implements UserDao{

	JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void deleteAllUsers() {
		String query = "delete from spring_users ";
		jdbcTemplate.update(query);
	}
	public void deleteUserById(String userId) {
		String query = "delete from spring_users where user_id = ? ";
		jdbcTemplate.update(query, userId);
		
	}
	public List<User> getAllUsers() {
		
		String query = "select * from spring_users order by user_id asc ";
		return jdbcTemplate.query(query, new UserRowMapper());
	}
	public User getUserById(String userId) {
		String query = "select * from spring_users where user_id = ? ";
		return jdbcTemplate.queryForObject(query, new UserRowMapper(), userId);
	}
	@Override
	public List<User> getUsersByName(String userName) {
		String query = "select * from spring_users where user_name = ? order by user_id asc ";
		return jdbcTemplate.query(query, new UserRowMapper(), userName);
	}
	public void insertUser(User user) {
		String query = "insert into spring_users values (?,?,?,?,sysdate ) ";
		jdbcTemplate.update(query, user.getId(),user.getName(),user.getPassword(), user.getEmail());
	}
	@Override
	public void updateUser(User user) {
		String query = "update spring_users set user_password = ? user_email = ? where user_id = ? ";
		jdbcTemplate.update(query, user.getPassword(), user.getEmail(), user.getId());
		
	}
	
	class UserRowMapper implements RowMapper<User>{
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getString("user_id"));
			user.setName(rs.getString("user_name"));
			user.setPassword(rs.getString("user_password"));
			user.setEmail(rs.getString("user_email"));
			return user;
		}
	}
	
}
