package kr.co.jhta.intercepter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.jhta.dao.RoleDao;
import kr.co.jhta.vo.User;

//인증 여부를 체크하는 인터셉터
// 이미 가입을 했고, 정상적인 로그인 과정을 
public class AuthenticationIntercepter extends HandlerInterceptorAdapter{

	@Autowired
	RoleDao roleDao;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		/*
		 *1. 요청 url 정보 조회
		 *2. 해당 url 에 접근하기 위한 접근 권한 조회
		 *3. 접근권한이 없으면 다음 인터셉터 혹은 컨트롤러가 실행되도록한다.
		 *4. 접근권한이 있으면
		 *	로그인된 사용자인지 확인하고,
		 *	로그인된 사용자라면 다음 인터셉터 혹은 컨트롤러가 실행되도록한다.
		 *	로그인된 사용자가 아니라면 로그인 폼 페이지로 이동시킨다.
		 *
		 * 결론
		 * 	접근 권하이 요구되는 url 요청인데, 로그인된 사용자가 아니라면
		 *   로그인 페이지로 이동시킨다.
		 * 
		 */
		
		//요청 uri 조회
		String requestURI = request.getRequestURI();
		// 요청 url에 접근하기 위해서 필요한 권한조회 및 요청 객체에 담기
		List<String> reqRoles = roleDao.getRolesByUrl(requestURI);
		request.setAttribute("reqRoles", reqRoles);
		
		// 접근 권한이 필요한 경우- 우선로그인된 사용자인지 체크
		if (!reqRoles.isEmpty()) {
			// 세션에서 로그인된 사용자 정보 조회
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("LOGIN_USER");
			// 로그인된 사용자 정보가 존재하지 않으면 로그인 폼으로 이동
			if (user == null) {
				response.sendRedirect("/signin.do?error=deny");
				return false;
			} 
			
			//로그인 된 사용자 정보가 존재하면,
			// 인증과정 통과여부와 로그인된 사용자가 소유한 접근 권한을 요청객체에 담기
			request.setAttribute("auth", "pass");
			request.setAttribute("hasRoles", user.getRoles());
		} else {
			//접근 권한이 필요없는 경우- 인증과정 통과여부를 skip으로 요청객체에 담기
			request.setAttribute("auth", "skip");
		}
		
		
		return true;
	}
	
}
