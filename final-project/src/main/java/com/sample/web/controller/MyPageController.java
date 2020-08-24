package com.sample.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.sample.service.*;
import com.sample.utils.NumberUtil;
import com.sample.web.view.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sample.web.security.Auth;
import com.sample.web.view.Qna;
import com.sample.web.view.Reserve;
import com.sample.web.view.User;

@Controller
@RequestMapping("/mypage")
public class MyPageController {
	final
	ReserveService reserveService;
	final
	UserService userService;
	final
	PerformanceService performanceService;
	final
	QnaService qnaService;
	final
	PaymentService paymentService;

	@Autowired
	public MyPageController(ReserveService reserveService, UserService userService, PerformanceService performanceService, QnaService qnaService, PaymentService paymentService) {
		this.reserveService = reserveService;
		this.userService = userService;
		this.performanceService = performanceService;
		this.qnaService = qnaService;
		this.paymentService = paymentService;
	}


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
	@GetMapping("/myperformance.do")
	public String myPerformance() {
		System.out.println("load myperformance page");
		return "mypage/myperformance";
	}
	
	@PostMapping("/myperformance.do")
	@ResponseBody
	public Map<String, Object> myPerformance(@RequestBody User user) {
		System.out.println("request to mypage controller href :'POST' /myperformance.do");
		
		List<Reserve> reserves = reserveService.getReservesByUserId(user.getId());
		Map<String, Object> map = new HashMap<>();

		System.out.println(reserves==null);

		map.put("reserves", reserves);


		return map;
	}
	@Auth
	@RequestMapping("/myperformanceDetail.do")
	public String myPerformanceDetail(@RequestParam("reserveId") String reserveId, HttpSession session,Model model) {
		int id = NumberUtil.stringToInt(reserveId);
		// user
		User user = (User)session.getAttribute("LOGIN_USER");
		user = userService.getUserDetail(user.getId());
		model.addAttribute("user", user);
		// reserve
		Reserve reserve = reserveService.getReserveDetail(id);
		model.addAttribute("reserve", reserve);
		model.addAttribute("performance", reserve.getPerformance());
		model.addAttribute("hall", reserve.getPerformance().getSchedule().get(0).getHallinfo());
		model.addAttribute("mate", reserve.getMate());
		// payment
		Payment payment = paymentService.getPaymentByReserveId(id);
		model.addAttribute("payment", payment);
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
