package kr.co.jhta.di.di.step1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jhta.di.di.SmsMessageSender;

@Service
public class EventNotificationServiceImp implements NotificationService{


	@Autowired
	private SmsMessageSender sms;
	
	public void notice(String dept, String subject, String content) {
		
		sms.send(dept, "모든고객", "1", "내용");
	}
	
}
