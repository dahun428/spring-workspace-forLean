package kr.co.jhta.dto;

import java.util.Date;
import java.util.List;

import kr.co.jhta.vo.Comment;

public class BlogDto {
 
	private int no;
	private String title;
	private String writer;
	private String content;
	private int likes;
	private int commentCount;
	private List<Comment> comments;
	private Date createdDate;
	
	
	public BlogDto() {
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "BlogDto [no=" + no + ", title=" + title + ", writer=" + writer + ", content=" + content + ", likes="
				+ likes + ", commentCount=" + commentCount + ", comments=" + comments + ", createdDate=" + createdDate
				+ "]";
	}

	
}
