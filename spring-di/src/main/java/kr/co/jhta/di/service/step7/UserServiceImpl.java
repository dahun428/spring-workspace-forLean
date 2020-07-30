package kr.co.jhta.di.service.step7;

import java.util.ArrayList;
import java.util.List;

import kr.co.jhta.di.vo.User;

public class UserServiceImpl implements UserService {

	public List<User> getAllUser() {
		
		List<User> users = new ArrayList<>();
		User user1 = new User("1", "홍길동", "010-4567-4545", "sms");
		User user2 = new User("2", "유산슬", "010-7777-4545", "kakaotalk");
		User user3 = new User("3", "강호동", "010-6666-4545","sms");
		User user4 = new User("4", "이효리", "010-1111-4545","kakaotalk");
		
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		
		return users;
	}

}
