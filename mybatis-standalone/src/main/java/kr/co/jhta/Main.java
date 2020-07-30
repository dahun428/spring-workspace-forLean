package kr.co.jhta;

import java.util.List;

import kr.co.jhta.dao.UserDao;
import kr.co.jhta.vo.User;

public class Main {

	public static void main(String[] args) {
		
		UserDao userDao = new UserDao();
//		User user1 = new User("hone3","홍길동","zxcv1234","hong@mail.com");
//		userDao.insertUser(user1);
//		System.out.println("새사용자 등록 완료");
//		
		System.out.println("모든 사용자 조회하기");
		List<User> users1 = userDao.getAllUsers();
		for(User user : users1) {
			System.out.println(user);
		}
		System.out.println();
		
		
//		System.out.println("특정 아이디의 사용자 조회하기");
//		User user2 = userDao.getUserById("123");
////		System.out.println(user2);
//		if(user2 != null) {
//			System.out.println(user2);
//		} else {
//			System.out.println("지정된 아이디의 사용자가 존재하지 않습니다.");
//		}
//		int userCount =  userDao.getUsersCount();
//		System.out.println(userCount);
		
//		System.out.println("특정 이메일 사용자 조회하기  => 리턴타입 리스트");
//		List<User> users3 = userDao.getUserByEmail("123");
//		if(users3 != null) {
//			System.out.println(users3);
//		} else {
//			System.out.println("지정된 아이디의 사용자가 존재하지 않습니다.");
//		}
//		System.out.println("특정 이름 사용자 조회하기 => 리턴 타입 리스트");
//		List<User> users4 = userDao.getUsersByName("123");
//		if(users4 != null) {
//			System.out.println(users4);
//		} else {
//			System.out.println("지정된 아이디의 사용자가 존재하지 않습니다.");
//		}
//		
//		System.out.println("특정 아이디 사용자 삭제하기 ==> 리턴 타입 없음");
//		userDao.deleteUserById("hone3");
//		System.out.println("사용자 삭제 완료");
//		
//		System.out.println("특정 아이디 사용자 업데이트 하기 ==> 리턴 타입 없음");
//		User user5 = new User("hone3","강감찬","vvv","dd@mm.com");
//		System.out.println("바꿀 사용자 : " + user5);
//		userDao.updateUser(user5);
//		System.out.println("업데이트 완료");
		
	}
}
