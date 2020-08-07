package kr.co.jhta.vo;

import java.util.List;
import java.util.Set;

public class UrlRole {

	private String path;
	private List<String> roles;
	
	public UrlRole() {
		// TODO Auto-generated constructor stub
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UrlRole [path=" + path + ", roles=" + roles + "]";
	}

	
}
