package kr.co.jhta.di.vo;

public class User {

	private String id;
	private String name;
	private String tel;
	private String receiveType;

	public User() {}

	public User(String id, String name, String tel, String receiveType) {
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.receiveType = receiveType;
	}

	public User(String id, String name, String tel) {
		this.id = id;
		this.name = name;
		this.tel = tel;
	}

	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}
	public String getReceiveType() {
		return receiveType;
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


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", tel=" + tel + "]";
	}


}
