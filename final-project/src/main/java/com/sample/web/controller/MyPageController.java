package com.sample.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.service.ReserveService;
import com.sample.service.UserService;
import com.sample.web.security.Auth;
import com.sample.web.view.Reserve;
import com.sample.web.view.User;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	@Autowired
	ReserveService reserveService;
	@Autowired
	UserService userService;
	
	@Auth
	@RequestMapping("/mypagemain.do")
	public String main(HttpSession session, Model model) {
		User user = (User)session.getAttribute("LOGIN_USER");
		user = userService.getUserDetail(user.getId());
		System.out.println(user.getId());
		Reserve reserve = reserveService.getLastestReserveByUserId(user.getId());
		System.out.println(reserve);
		model.addAttribute("user", user);
		return "mypage/mypagemain";
	}
	@RequestMapping("/myperformance.do")
	public String myPerformance() {
		return "mypage/myperformance";
	}
	@RequestMapping("/myperformanceDetail.do")
	public String myPerformanceDetail() {
		return "mypage/myperformanceDetail";
	}
}
