package kr.co.jhta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jhta.sender.MessageSender;

@Service
public class EventServiceImpl implements EventService{


	@Autowired
	private MessageSender ms;
	public void setMs(MessageSender ms) {
		this.ms = ms;
	}
	
	public void send(String dept, String subject, String content) {
		ms.send(dept, "전고객", subject, content);
	
	}
}
