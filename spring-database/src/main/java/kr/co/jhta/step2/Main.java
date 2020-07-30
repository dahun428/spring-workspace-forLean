package kr.co.jhta.step2;

import java.sql.SQLException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.co.jhta.dao.BookDAO;
import kr.co.jhta.vo.Book;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		String conf = "classpath:/spring/context.xml";
		ApplicationContext ctx = new GenericXmlApplicationContext(conf);
		BookDAO dao = ctx.getBean(BookDAO.class);
		List<Book> books = dao.getAllBooks();
		for(Book book : books) {
			System.out.println(book);
		}
		
	}
	
	
}
