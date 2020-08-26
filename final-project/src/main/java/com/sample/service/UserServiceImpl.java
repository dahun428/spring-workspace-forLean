package com.sample.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.dao.UserDao;
import com.sample.dto.UserInfoDto;
import com.sample.web.view.Coupon;
import com.sample.web.view.User;
import com.sample.web.view.UserPoint;

@Transactional
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public void addUser(User user) {
		userDao.insertUser(user);
		userDao.insertUserSub(user);
		userDao.insertUserAddr(user);
		
	}

	public User loginUser(String id, String password) {
		User user = userDao.getUserById(id);
		
		if(user == null) {
			return null;
		}
		if (!user.getPassword().equals(password)) {
			return null;
		}
		return user;
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public User getUserDetail(String id) {
		return userDao.getUserById(id);
	}

	@Override
	public List<Coupon> getCouponByUserId(String userId) {
		return userDao.getCouponsByUserId(userId);
	}

	@Override
	public User getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.getUserByEmail(email);
	}

	@Override
	public User getPasswordByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.getPasswordByEmail(email);
	}

	@Override
	public void addUserPointHistory(UserPoint userPoint) {
		userDao.insertUserPoint(userPoint);		
	}
	@Override
	public List<UserInfoDto> getAllUsers(Map<String, Object> param) {
		
		return userDao.getAllUsers(param);
	}

	@Override
	public UserInfoDto getUserInfoDetail(String id) {
		
		return userDao.getUserInfoDetail(id);
	}

	
	@Override
	public int getAllUsersCount(Map<String, Object> param) {
	
		return userDao.getAllUsersCount(param);
	}
	

}
