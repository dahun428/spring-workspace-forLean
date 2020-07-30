package kr.co.jhta;

import java.util.List;
import java.util.Scanner;

import kr.co.jhta.dao.BlogDao;
import kr.co.jhta.dao.CommentDao;
import kr.co.jhta.dao.UserDao;
import kr.co.jhta.dto.BlogDto;
import kr.co.jhta.service.BlogService;
import kr.co.jhta.vo.Blog;
import kr.co.jhta.vo.Comment;


public class Main {

	
	public static void main(String[] args) {

		UserDao userdao = new UserDao();
		BlogDao blogdao = new BlogDao();
		BlogService service = new BlogService();
		CommentDao commentdao = new CommentDao();
		
//		System.out.println("블로그 등록");
//		Blog blog = new Blog("제목", "jung", "콘텐츠 새로운");
//		blogdao.insertBlog(blog);
//		System.out.println("모든 게시글 조회");
//		List<Blog> blogs = blogdao.getAllBlogs();
//		System.out.println(blogs);
//		System.out.println("특정번호 게시글 조회");
//		System.out.println(blog2);
		
//		BlogDto blog3 = service.getBlogDtoWithComment(22);
//		System.out.println(blog3);
		
		Comment comment = new Comment("jung", "good", 22);
		service.insertCommentWithIncreaseOfBlogCommentCount(comment);
		
		
		
		
//		Scanner sc = new Scanner(System.in);
//		try {
//			while(true) {
//				
//				System.out.println();
//				
//				
//				System.out.println("[사용자관리]");
//				System.out.println("===================================================================");
//				System.out.println("1.전체조회 2.조회(아이디) 3.조회(이름) 4.등록 5.삭제(아이디) 6.전체 0.종료");
//				System.out.println("===================================================================");
//
//				System.out.println("메뉴 선택 : ");
//				int menuNo = sc.nextInt();
//
//				if(menuNo == 1) {
//
//					System.out.println("[전체 조회]");
//
//
//
//
//
//
//
//				} else if (menuNo == 2) {
//					System.out.println("[아이디로 조회]");
//
//				} else if (menuNo == 3) {
//					System.out.println("[이름으로 조회]");
//
//				} else if (menuNo == 4) {
//					System.out.println("[신규사용자 등록]");
//
//					System.out.println("아이디 입력 > ");
//					String id = sc.next();
//					System.out.println("비밀번호 입력 > ");
//					String password = sc.next();
//					System.out.println("이름 입력 > ");
//					String userName = sc.next();
//					System.out.println("이메일 입력 > ");
//					String email = sc.next();
//
//					User user = new User();
//					user.setId(id);
//					user.setPassword(password);
//					user.setName(userName);
//					user.setEmail(email);
//					dao.insertUser(user);
//					System.out.println("<<사용자 등록 완료>>");
//
//				} else if (menuNo == 5) {
//					System.out.println("[아이디로 삭제]");
//
//				} else if (menuNo == 6) {
//					System.out.println("[전체 삭제]");
//
//				} else if (menuNo == 0) {
//					ctx.destroy();
//					System.out.println("<<프로그램 종료>>");
//					break;
//				}
//
//
//			}
//		} catch(DataAccessException e) {
//			e.printStackTrace();
//		}


		
		
		
		
	}
}
