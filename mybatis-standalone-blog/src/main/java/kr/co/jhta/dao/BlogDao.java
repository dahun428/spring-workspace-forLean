package kr.co.jhta.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.jhta.dto.BlogDto;
import kr.co.jhta.util.MybatisUtil;
import kr.co.jhta.vo.Blog;

public class BlogDao {
	public void insertBlog(Blog blog) { 

		SqlSession session = MybatisUtil.getSqlSession();
		try {
			
			session.insert("insertBlog" , blog);
			session.commit();
		} finally {
			session.close();
		}
		
	}
	public List<Blog> getAllBlogs() { 

		SqlSession session = MybatisUtil.getSqlSession();
		try {
			List<Blog> blogs = session.selectList("getAllBlogs");
			return blogs;
		} finally {
			session.close();
		}
		
	}
	public Blog getBlogByNo(int blogNo) {
		
		SqlSession session = MybatisUtil.getSqlSession();
		try {
			System.out.println("블로그 번호 : " + blogNo);
			Blog blog = session.selectOne("getBlogByNo", blogNo);
			System.out.println("검색된 블로그 : " + blog);
			return blog;
		} finally {
			session.close();
		}
		
	}	
//	public BlogDto getBlogDtoByNo(int blogNo) {
//		SqlSession session = MybatisUtil.getSqlSession();
//		try {
//			BlogDto blog = session.selectOne("getBlogDtoByNo",blogNo);
//			
//			
//			
//		} finally {
//			
//		}
//	}
	
	public void updateBlog(Blog blog) { 

		SqlSession session = MybatisUtil.getSqlSession();
		try {
			session.update("updateBlog", blog);
			session.commit();
		} finally {
			session.close();
		}
		
	}
	
	
}
