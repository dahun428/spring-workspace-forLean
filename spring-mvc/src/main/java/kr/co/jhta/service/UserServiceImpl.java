package kr.co.jhta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.jhta.dao.RoleDao;
import kr.co.jhta.dao.UserDao;
import kr.co.jhta.vo.User;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	
	public void addNewUser(User user) {

		userDao.insertUser(user);
	}
	public User getUserDetail(String userId) {
		
		System.out.println("userServiceImpl 시도");
		User user = userDao.getUserById(userId);
		System.out.println("userServiceImpl 검사 : " + userDao.getUserById(userId));
		return user;
	}
	public User login(String userId, String userPw) {
		User user = userDao.getUserById(userId);
		System.out.println("service_User : "+ user);
		if(user == null) {
			return null;
		}
		if(!user.getPassword().equals(userPw)) {
			return null;
		}
		
		//로그인 체크를 통과한 사용자가 소유한 접근 권한을 조회한다.
		List<String> roles = roleDao.getRolesUserById(userId);
		user.setRoles(roles);
		
		return user;
	}
	
}
