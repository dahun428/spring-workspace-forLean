package kr.co.jhta.di.di;

public interface MessageSender {

	void send(String from, String to, String subject, String content);

}
