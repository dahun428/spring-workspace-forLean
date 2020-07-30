package kr.co.jhta.di.di.step2;

import org.springframework.context.support.GenericXmlApplicationContext;

import kr.co.jhta.di.di.SmsMessageSender;

public class Main {

	
	public static void main(String[] args) {
		String conf = "classpath:\\spring\\context-step2.xml";

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(conf);
		AlarmServiceIml alarm = ctx.getBean(AlarmServiceIml.class);
		alarm.alarm("전쟁상황입니다");
		
	}
}
