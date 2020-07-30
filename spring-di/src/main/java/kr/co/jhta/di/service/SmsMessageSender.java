package kr.co.jhta.di.service;

public class SmsMessageSender implements MessageSender{

	private String provider;
	
	public void setProvider(String provider) {
		this.provider = provider;
	}
	
	public void send(String from, String to,String subject, String content) {

		System.out.println("["+provider + "] 통신사를 이용합니다.");
		System.out.println("SMS 발신자 : " + from);
		System.out.println("SMS 수신자 : " + to);
		System.out.println("SMS 제목 : " + subject);
		System.out.println("SMS 내용 : " + content);
		System.out.println("-------------------------------------");
	}
}
