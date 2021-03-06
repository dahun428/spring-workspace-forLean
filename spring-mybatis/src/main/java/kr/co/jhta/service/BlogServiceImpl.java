package kr.co.jhta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.jhta.dao.BlogCommentDao;
import kr.co.jhta.dao.BlogDao;
import kr.co.jhta.dto.BlogDetailDto;
import kr.co.jhta.vo.Blog;
import kr.co.jhta.vo.Comment;

@Service
@Transactional
public class BlogServiceImpl implements BlogService{

	@Autowired
	private BlogDao blogDao;
	@Autowired
	private BlogCommentDao commentDao;
	
	public void addNewBlog(Blog blog) {
		blogDao.insertBlog(blog);
	}
	public void addNewComment(Comment comment) {
		commentDao.insertComment(comment);
	}
	public List<Blog> getAllBlogs() {
		return blogDao.getBlogs();
	}
	public BlogDetailDto getBlogDetail(int blogNo) {
		Blog blog = blogDao.getBlogByNo(blogNo);
		List<Comment> comments = commentDao.getCommentsByBlogNo(blogNo);
		BlogDetailDto dto = new BlogDetailDto(blog, comments);
		return dto;
	}
	public void removeBlog(int blogNo) {
		commentDao.deleteCommentsByBlogNo(blogNo);
		blogDao.deleteBlogsByNo(blogNo);
	}
	public void removeComment(int commentNo) {
		commentDao.deleteCommentByNo(commentNo);
	}
	
	
	
}
