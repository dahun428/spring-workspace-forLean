package kr.co.jhta.di.service.step8;

import java.util.List;
import java.util.Map;
import java.util.Set;

import kr.co.jhta.di.service.MessageSender;
import kr.co.jhta.di.service.step7.UserService;
import kr.co.jhta.di.vo.User;

public class NoticeServiceUsingMap implements NoticeService{

	private UserService userService;
	private Map<String ,MessageSender> messageSenders;
	
	public void setMessageSenders(Map<String, MessageSender> messageSenders) {
		this.messageSenders = messageSenders;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	
	
	public void notice(String dept, String subject, String content) {
		List<User> users = userService.getAllUser();
		for(User user : users) {
			//사용자가 지정한 수신방식 조회
			String recieveType = user.getReceiveType();
			System.out.println("수신방식" + recieveType);
			//수신 방식에 맞는 메시지 발신 객체를 map 객체에서 꺼낸다.
			MessageSender sender = messageSenders.get(recieveType);
			System.out.println("메시지 발신 객체 : " + sender);
			
			//획득된 메시지 발샌 객체를 사용해서 메시지를 발송한다.
			sender.send(dept, user.getTel(), subject, content);
			System.out.println();
		}
		
		
	}
	
}
