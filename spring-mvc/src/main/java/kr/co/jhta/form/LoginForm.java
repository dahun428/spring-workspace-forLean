package kr.co.jhta.form;

import javax.validation.constraints.NotBlank;

public class LoginForm {

	@NotBlank(message="아이디를 입력해주세요")
	private String userId;
	@NotBlank(message="비밀번호를 입력해주세요")
	private String userPw;
	
	public LoginForm() {
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	@Override
	public String toString() {
		return "LoginForm [userId=" + userId + ", userPw=" + userPw + "]";
	}
	
	
	
}
