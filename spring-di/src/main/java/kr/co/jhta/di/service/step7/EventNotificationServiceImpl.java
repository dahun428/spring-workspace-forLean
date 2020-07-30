package kr.co.jhta.di.service.step7;

import java.util.List;

import kr.co.jhta.di.service.MessageSender;
import kr.co.jhta.di.service.SmsMessageSender;
import kr.co.jhta.di.vo.User;

public class EventNotificationServiceImpl implements EventNotificationService{

	private MessageSender messageSender;
	private UserService userService;
	private String from;
	
	public void setMessageSender(SmsMessageSender messageSender) {
		this.messageSender = messageSender;
	}
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public void noticeEvent(String eventName, String eventContent) {
		
		List<User> list = userService.getAllUser();
		for(User user : list) {
			messageSender.send(from, user.getName(), eventName, eventContent);
		}
		
	}
	
}
