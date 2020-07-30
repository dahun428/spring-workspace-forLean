package kr.co.jhta.di.service.step8;

import java.util.List;
import java.util.Set;

import kr.co.jhta.di.service.MessageSender;
import kr.co.jhta.di.service.step7.UserService;
import kr.co.jhta.di.vo.User;

/**
 * 사용가능한 모든 MessageSender 류 객체를 이용해서 공지사항 전달 기능 제공
 * @author JHTA
 *
 */
public class NoticeServiceUsingSet implements NoticeService{

	private UserService userService;
	private Set<MessageSender> messageSenders;
	
	public void setMessageSenders(Set<MessageSender> messageSenders) {
		this.messageSenders = messageSenders;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void notice(String dept, String subject, String content) {
	
		List<User> users = userService.getAllUser();
		
		for(MessageSender messageSender : messageSenders) {
			System.out.println("사용중인 MessageSender 구현객체 : " + messageSender.getClass().getName());
			for(User user : users) {
				messageSender.send(dept, user.getTel(), subject, content);
			}
		}
		
	}
		
	
}
