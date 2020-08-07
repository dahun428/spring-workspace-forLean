package kr.co.jhta.vo;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class User {

	private String id;
	private String name;
	private String password;
	private String email;
	private String tel;
	private int age;
	private Date birth;
	private Date createdDate;
	private List<String> roles;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public List<String> getRoles() {
		return roles;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", tel=" + tel
				+ ", age=" + age + ", birth=" + birth + ", createdDate=" + createdDate + "]";
	}

	

}
