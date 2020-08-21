package com.sample.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.service.PerformanceService;
import com.sample.service.QnaService;
import com.sample.service.ReserveService;
import com.sample.service.UserService;
import com.sample.web.security.Auth;
import com.sample.web.view.Qna;
import com.sample.web.view.Reserve;
import com.sample.web.view.User;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	@Autowired
	ReserveService reserveService;
	@Autowired
	UserService userService;
	@Autowired
	PerformanceService performanceService;
	@Autowired
	QnaService qnaService;
	
	@Auth
	@RequestMapping("/mypagemain.do")
	public String main(HttpSession session, Model model) {
		User user = (User)session.getAttribute("LOGIN_USER");
		user = userService.getUserDetail(user.getId());
		user.setLikes(performanceService.getPerformanceByUserId(user.getId()));
		Reserve reserve = reserveService.getLastestReserveByUserId(user.getId());
		List<Qna> qnaList = qnaService.getQnasByUserId(user.getId()); 
		
		model.addAttribute("user", user);
		
		System.out.println(user.getLikes().size());
		
		model.addAttribute("lastReserve",reserve);
		model.addAttribute("qnaList", qnaList);
		
		System.out.println(qnaList.size());
		return "mypage/mypagemain";
	}
	@Auth
	@RequestMapping("/myperformance.do")
	public String myPerformance() {
		return "mypage/myperformance";
	}
	@Auth
	@RequestMapping("/myperformanceDetail.do")
	public String myPerformanceDetail() {
		return "mypage/myperformanceDetail";
	}
	@Auth
	@RequestMapping("/mymateroom.do")
	public String myMateRoom() {
		return "mypage/mymateroom";
	}
	@Auth
	@RequestMapping("/myprofile.do")
	public String myprofile() {
		return "mypage/myprofile";
	}
	@Auth
	@RequestMapping("/myLikes.do")
	public String myLikes() {
		return "mypage/myLikes";
	}
	@Auth
	@RequestMapping("/myCoupon.do")
	public String myCoupon() {
		return "mypage/myCoupon";
	}
	@Auth
	@RequestMapping("/myPoint.do")
	public String myPoint() {
		return "mypage/myPoint";
	}
}
