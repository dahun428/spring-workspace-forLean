package kr.co.jhta.di.service.step8;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		String conf = "kr\\co\\jhta\\di\\service\\step8\\context-step8-map.xml";

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(conf);
		
		NoticeService notice = ctx.getBean("noticeService", NoticeService.class);
		notice.notice("홍보팀", "카톡제목", "내용");
		
		ctx.destroy();
	}
}











