package kr.co.jhta.di.di;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("sms")
public class SmsMessageSender implements MessageSender{

	private String company;
	@Value("${message.sender.sms.company}")
	public void setCompany(String company) {
		this.company = company;
	}
	
	public void send(String from, String to, String subject, String content) {

		System.out.println("["+company + "] 통신사를 이용합니다.");
		System.out.println("SMS 발신자 : " + from);
		System.out.println("SMS 수신자 : " + to);
		System.out.println("SMS 제목 : " + subject);
		System.out.println("SMS 내용 : " + content);
		System.out.println("-------------------------------------");

	}
}
