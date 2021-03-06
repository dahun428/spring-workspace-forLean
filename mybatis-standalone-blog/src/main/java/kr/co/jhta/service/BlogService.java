package kr.co.jhta.service;

import java.util.List;

import kr.co.jhta.dao.BlogDao;
import kr.co.jhta.dao.CommentDao;
import kr.co.jhta.dao.UserDao;
import kr.co.jhta.dto.BlogDto;
import kr.co.jhta.vo.Blog;
import kr.co.jhta.vo.Comment;

public class BlogService {
	UserDao userdao = new UserDao();
	BlogDao blogdao = new BlogDao();
	CommentDao commentdao = new CommentDao();
	
	public BlogDto getBlogDtoWithComment(int blogNo) {
		Blog blog = blogdao.getBlogByNo(blogNo);
		List<Comment> comments = commentdao.getCommentsByBlogNo(blogNo);
		
		BlogDto blogDto = new BlogDto();
		blogDto.setNo(blog.getNo());
		blogDto.setTitle(blog.getTitle());
		blogDto.setWriter(blog.getWriter());
		blogDto.setLikes(blog.getLikes());
		blogDto.setContent(blog.getContent());
		blogDto.setComments(comments);
		blogDto.setCommentCount(blog.getCommentCount());
		blogDto.setCreatedDate(blog.getCreatedDate());
		return blogDto;
	}
	public void insertCommentWithIncreaseOfBlogCommentCount(Comment comment) {
		commentdao.insertComment(comment);
		Blog blog = blogdao.getBlogByNo(comment.getBlogno());
		System.out.println("현재 카운트 : " + blog.getCommentCount());
		blog.setCommentCount(blog.getCommentCount()+1);
		System.out.println("업데이트 이후 카운트 : " + blog.getCommentCount());
		blogdao.updateBlog(blog);
	}
	
}
