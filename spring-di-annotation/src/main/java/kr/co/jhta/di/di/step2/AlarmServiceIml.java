package kr.co.jhta.di.di.step2;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.jhta.di.di.MessageSender;

@Service
public class AlarmServiceIml implements AlarmService{

//	@Autowired
	@Resource
	MessageSender kakao;
	
	public void alarm(String message) {
		kakao.send("민방위", "전국민", "비상", message);
	}
}
