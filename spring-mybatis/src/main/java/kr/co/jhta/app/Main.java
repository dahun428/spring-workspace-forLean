package kr.co.jhta.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.co.jhta.service.UserService;
import kr.co.jhta.vo.User;

public class Main {
 
	
	public static void main(String[] args) {
		String conf = "classpath:/spring/context.xml";
		ApplicationContext ctx = new GenericXmlApplicationContext(conf);
		// 스프링컨테이너에서 UserService 구현 객체 찾아오기
		
		// 아래의 수행문의 UserServiceImpl 획득
		//@ Service
		// pulic class UserServiceImpl implements UserService{...}
		UserService userService = ctx.getBean(UserService.class);
		userService.addNewUser(new User("dahun", "name", "123pasword", "fault@nn"));
		
	}
}
