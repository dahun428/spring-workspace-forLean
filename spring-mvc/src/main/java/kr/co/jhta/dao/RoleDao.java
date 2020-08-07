package kr.co.jhta.dao;

import java.util.List;

public interface RoleDao {

	// 사용자가 가진 접근권한 조회하기
	List<String> getRolesUserById(String userId);
	//해당 url 에 접근하기 위해 필요한 접근권한 조회하기
	List<String> getRolesByUrl(String url);
	
}
