package kr.co.jhta.di.di.step1;

import org.springframework.context.support.GenericXmlApplicationContext;

import kr.co.jhta.di.di.SmsMessageSender;

public class Main {

	
	public static void main(String[] args) {
		String conf = "classpath:\\spring\\context-step1.xml";

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(conf);
//		SmsMessageSender sender = ctx.getBean(SmsMessageSender.class);
		EventNotificationServiceImp ens = ctx.getBean(EventNotificationServiceImp.class);
		
//		System.out.println(sender);
//		sender.send("홍보부", "010-1234-1245", "123", "123");	
//		
		
		ens.notice("홍보부", "제목", "내용");
		
	}
}
