package com.sample.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.dto.MateDetailDto;
import com.sample.service.MateService;
import com.sample.web.security.Auth;
import com.sample.web.view.Mate;
import com.sample.web.view.MateTag;
import com.sample.web.view.MateTimeLine;
import com.sample.web.view.User;

@Controller
@RequestMapping("/mate")

public class MateController {

	@Autowired
	MateService mateService;
	
	/**
	 * matelist 페이지에서 '참가'버튼을 클릭하면 해당 방으로 입장가능하다.
	 * 
	 * @param performanceId 필수값. performance_main 테이블의 id 값
	 * @param mateId 필수값. mate_main 테이블의 id 값
	 * @param model 해당방의 user의 정보, mate_category 테이블의 정보
	 * 		  	접근하고자하는 url 의 get 정보 (performanceId, mateId)값을 
	 * 			model로 보낸다.
	 * @return mate/matedetail.jsp 페이지이동
	 */
	@GetMapping("/matedetail.do")
	public String matedetail(@RequestParam("pid") int performanceId, 
			@RequestParam("mnum") int mateId, Model model) {
		List<Map<Integer, String>> categories = mateService.getMateAllCategory();
		List<User> users = mateService.getMateUserByMateId(mateId);
		model.addAttribute("mate", users);
		model.addAttribute("pid", performanceId);
		model.addAttribute("mnum", mateId);
		model.addAttribute("categories", categories);
		
		return "mate/matedetail";
	}
	/**
	 * 퍼포먼스 메인 아이디에 해당하는 메이트 방 리스트를 조회한다.
	 * @return mate/matelist.jsp 페이지
	 */
	@GetMapping("/mate.do")
	public String mate(@RequestParam("pid") int performanceId, HttpSession session, Model model) {
		
		/*
		 * 해당 아이디가 접근 권한이 있는지 체크하기
		 * 유저가 특정 공연을 결제를 완료했으면 접근가능
		 */
		//User user = (User) session.getAttribute("LOGIN_USER");
		//if(user == null) {
		//	return "redirect:/home.do";
		//}
		List<Mate> mates = mateService.getMatesByPerformanceId(performanceId);
		List<Map<Integer, String>> mateCat = mateService.getMateAllCategory();
		Integer mateCount = mateService.getCountMateByPerformanceId(performanceId);
		
		model.addAttribute("mateList", mates);
		model.addAttribute("category", mateCat);
		model.addAttribute("mateCount", mateCount);
		
		return "mate/matelist";
	}
//	@GetMapping("/jsonmate.do")
//	public @ResponseBody List<Mate> jsonMate(@RequestParam("pid") int performanceId){
//		
//		return mateService.getMatesByPerformanceId(performanceId);
//	}
	/**
	 * 해당 방의 정보를 ajax 데이터와 연결하여 불러온다.
	 * @param performanceId 
	 * @param mateId
	 * @return
	 */
	@Auth
	@PostMapping("/matedetail.do")
	@ResponseBody
	public MateDetailDto mateRoom(@RequestParam("pid") int performanceId, 
							@RequestParam("mnum") int mateId, HttpSession session) {
		
		
		//전부 ajax로 가져온다.
		
		//메이트 조건 -> 메이트 아이디, 공연 아이디
		//해당 메이트 방의 공연 정보, 유저정보, 메이트 정보, 타임라인 정보를 가져온다.
		//제약조건 : 메이트 방에 해당하는 유저가 있는지 검사한다.
		//제약조건 : 세션값이 있는지 해당하고, 글을 올리는 유저를 검사한다.
		MateDetailDto detail = mateService.getMateRoomDetail(mateId, performanceId);
		//로그인한 유저정보를 가져온다.
		User user = (User) session.getAttribute("LOGIN_USER");
		String userId = null;
		if(user != null) {
			userId = user.getId();
			detail.setSessionUserId(userId);
		}
		
		return detail;
	}
	@Auth
	@RequestMapping("/Jmatedetail.do")
	@ResponseBody
	public MateDetailDto mateRoomJson(@RequestParam("pid") int performanceId, 
							@RequestParam("mnum") int mateId) {
		//전부 ajax로 가져온다.
		//메이트 조건 -> 메이트 아이디, 공연 아이디
		//해당 메이트 방의 공연 정보, 유저정보, 메이트 정보, 타임라인 정보를 가져온다.
		//제약조건 : 메이트 방에 해당하는 유저가 있는지 검사한다.
		//제약조건 : 세션값이 있는지 해당하고, 글을 올리는 유저를 검사한다.
		MateDetailDto detail = mateService.getMateRoomDetail(mateId, performanceId);
		return detail;
	}
	/**
	 * 해당 메이트 방의 timeline 입력시에 정보를 가져와서 insert 한다.
	 * @param mateId ajax에서 mateId를 받아온다.
	 * @param content ajax에서 content를 받아온다.
	 * @param session 세션에서 userId를 받아온다.
	 */
	@Auth
	@RequestMapping("/timeline.do")
	@ResponseBody
	public void addTimeLine(@RequestParam("mnum") int mateId, 
							@RequestParam("content") String content,
									HttpSession session) {
		User user = (User) session.getAttribute("LOGIN_USER");
		if(user == null) {
			throw new RuntimeException("접근 권한이 없습니다.");
		}
		MateTimeLine mateTimeLine = new MateTimeLine();
		mateTimeLine.setUser(user);
		mateTimeLine.setId(mateId);
		mateTimeLine.setContent(content);

		mateService.addMateTimeLineByMateIdAndUserId(mateTimeLine);
	}
	/**
	 * 해당 메이트방을 실시간으로 감시해서 새로운 채팅 내역이 있으면 갱신한다.
	 * @param performanceId
	 * @param mateId
	 * @return
	 */
	@Auth
	@RequestMapping("/timelineInterval.do")
	@ResponseBody
	public Map<String, Object> mateTimeLineCount(@RequestParam("pid") int performanceId, 
			@RequestParam("mnum") int mateId, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		map.put("service", mateService.getMateTimeLineCountByMateId(mateId));
		
		User user = (User) session.getAttribute("LOGIN_USER");
		if(user != null) {
			map.put("sessionId", user.getId());
		}
		
		return map;
	}
	@Auth
	@RequestMapping("/addTag.do")
	@ResponseBody
	public List<MateTag> addmateTag(@RequestParam("mnum") int mateId, 
							@RequestParam("tags") List<String> tags, HttpSession session) {
		
		return mateService.addHashTag(mateId, tags);
	}
	@Auth
	@RequestMapping("/updateCat.do")
	@ResponseBody
	public int updateMateCategory(@RequestParam("mnum") int mateId,
			@RequestParam("mcat") int categoryId) {
		mateService.updateMateCategoryByMateId(categoryId, mateId);
		
		return categoryId;
	}
	
	
	
}
