package kr.co.jhta.vo;

import java.util.Date;

public class Comment {
	//
	//	comment_no number(6) primary key,								-- 댓글 번호
	//	comment_writer varchar2(20) references spring_users (user_id),	-- 댓글 작성자 아이디
	//	comment_content varchar2(500) not null,							-- 댓글 내용
	//	blog_no number(6) references spring_blogs (blog_no),			-- 이 댓글의 게시글 번호
	//	comment_create_date date default sysdate			

	private int no;
	private String writer;
	private String content;
	private int blogno;
	private Date createdDate;


	public Comment() {
	}



	public Comment(String writer, String content, int blogno) {
		this.writer = writer;
		this.content = content;
		this.blogno = blogno;
	}



	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getWriter() {
		return writer;
	}


	public void setWriter(String writer) {
		this.writer = writer;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getBlogno() {
		return blogno;
	}


	public void setBlogno(int blogno) {
		this.blogno = blogno;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	@Override
	public String toString() {
		return "Comment [no=" + no + ", writer=" + writer + ", content=" + content + ", blogno=" + blogno
				+ ", createdDate=" + createdDate + "]";
	}





}
