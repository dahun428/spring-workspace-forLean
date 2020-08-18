package com.sample.web.view;

import java.util.Date;

public class MateTimeLine {
	private int id;
    private String content;
    private Date regDate;
    private User user;

    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

	@Override
	public String toString() {
		return "MateTimeLine [id=" + id + ", content=" + content + ", regDate=" + regDate + ", user=" + user + "]";
	}
    
}