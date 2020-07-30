package kr.co.jhta.step2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.co.jhta.service.EventService;

public class Main {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(AppConfig.class); // java-based 설정 정보가 포함된 자바 클래스 등록;
		ctx.refresh();					// 스프링 컨테이너를 갱신
		
		EventService service = ctx.getBean(EventService.class);
		service.send("경리부", "급여", "11원");
		
		
	}
	
	
	
}
