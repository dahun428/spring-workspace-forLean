package kr.co.jhta.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.jhta.util.MybatisUtil;
import kr.co.jhta.vo.User;

public class UserDao {

	//insert : N종류의 데이터 , parameter : User
	public void insertUser(User user) {
		SqlSession session = MybatisUtil.getSqlSession();
		try {
			session.insert("insertUser",user);
			session.commit();
		} finally {
			session.close();
		}
	}
	//delete : 없음 ,paramter : 없음
	public void deleteAllUsers() {
		
	}
	//delete : 기본자료형 (String)
	public void deleteUserById(String userId) {
		SqlSession session = MybatisUtil.getSqlSession();
		try {
			session.delete("deleteUserById", userId);
			session.commit();
		} finally {
			session.close();
		}
	}
	//update : N종류의 데이터 , paramter : User , result : 없음
	public void updateUser(User user) {
		SqlSession session = MybatisUtil.getSqlSession();
		try {
			session.update("updateUser", user);
			session.commit();
		} finally {
			session.close();
		}
	}
	
	// select: N행 N열  paramter : 없음 result: User 최종값:List<User>
	public List<User> getAllUsers(){
		
		SqlSession session = MybatisUtil.getSqlSession();
		try {
			List<User> users = session.selectList("getAllUsers");
			return users;
		} finally {
			session.close();
		}
	}
	// select : N행 N열, paramter : string, result : User , 최종값 : List<User>
	public List<User> getUsersByName(String userName){
		
		SqlSession session = MybatisUtil.getSqlSession();
		try {
			List<User> users = session.selectList("getUsersByName", userName);
			return users;
		} finally {
			session.close();
		}
		
	}
	// select: 1행 1열, parameter : string , result : User , 최종값 : 
	public User getUserById(String userId) {
		SqlSession session = MybatisUtil.getSqlSession();
		try {
			User user = session.selectOne("getUserById", userId);
			return user;
		} finally {
			session.close();
		}
		
	}
	// select : 1행 1열, parameter : string , result:User객체
	public List<User> getUserByEmail(String userEmail){
		SqlSession session = MybatisUtil.getSqlSession();
		try {
			List<User> users = session.selectList("getUserByEmail", userEmail);
			return users;
		} finally {
			session.close();
		}
	}
	// select : 1행 1열, parameter : 없음 , result : int , 최종값 : int
	public int getUsersCount() {
		SqlSession session = MybatisUtil.getSqlSession();
		try {
			int userCount = session.selectOne("getUsersCount");
			return userCount;
		} finally {
			session.close();
		}
		
	}
	
	
	
	
	
}
