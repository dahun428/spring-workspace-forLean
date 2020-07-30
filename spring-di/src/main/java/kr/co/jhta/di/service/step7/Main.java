package kr.co.jhta.di.service.step7;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		String conf = "kr\\co\\jhta\\di\\service\\step7\\context-step7.xml";

		ApplicationContext ctx = new GenericXmlApplicationContext(conf);
		
		EventNotificationService event = ctx.getBean("eventService", EventNotificationService.class);
		event.noticeEvent("할인 100% 이벤트", "올해 회원 상대 올 세일 이벤트 실행!");
		
	}
}











