package kr.co.jhta.vo;

import java.util.Date;

public class Blog {
	//	blog_no number(6) primary key,								-- 게시글 번호
	//	blog_title varchar2(200) not null,							-- 제목
	//	blog_writer varchar2(20) references spring_users (user_id),	-- 작성자 아이디
	//	blog_content varchar2(1000) not null,						-- 내용
	//	blog_likes number(6) default 0,								-- 조회수
	//	blog_comment_counts number(6) default 0,					-- 댓글 갯수
	//	blog_create_date date default sysdate 		

	private int no;
	private String title;
	private String writer;
	private String content;
	private int likes;
	private int commentCount;
	private Date createdDate;

	public Blog() {
	}

	public Blog(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Blog [no=" + no + ", title=" + title + ", writer=" + writer + ", content=" + content + ", likes="
				+ likes + ", commentCount=" + commentCount + ", createdDate=" + createdDate + "]";
	}





}
