package com.sample.web.controller;


import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.dto.PerformanceDetailDto;
import com.sample.service.PerformanceService;
import com.sample.web.form.PerformanceSearchForm;



@Controller
@RequestMapping("/performance")
public class PerformanceContoller {

	@Autowired
	private PerformanceService performanceService;
	
	@GetMapping("/list.do")
	public String list(Model model
			, @RequestParam("category") String category
			, @RequestParam(value = "pageNo", defaultValue="1") int pageNo
			, @RequestParam(value = "rows", defaultValue = "5") int rows
			, @RequestParam(value= "order", defaultValue = "dateOrder") String listOrder
			, @RequestParam(value = "title", defaultValue="") String title
			, @RequestParam(value = "genre", required=false) String[] searchGenres
			, @RequestParam(value = "startDay", defaultValue = "") String startDay 
			, @RequestParam(value = "endDay", defaultValue = "") String endDay
			, @RequestParam(value= "age", defaultValue="0") String age) {
		
		
		System.out.println("pageNo: " +pageNo);
		System.out.println("rows: " + rows);
		System.out.println("order: " + listOrder);
		System.out.println("title: " + title);
		System.out.println("genres: " + Arrays.toString(searchGenres));
		
		System.out.println(Arrays.toString(searchGenres));
		System.out.println("startDate: " + startDay);
		System.out.println("endDate: " + endDay);
		System.out.println("age: " + age);
		
		// 검색조건 표시하기 위한 폼 만들기
		//PerformanceSearchForm performanceForm = new PerformanceSearchForm();
		//performanceForm.setCategory(category);
		
		// 조회하기 위한 맵 만들기 
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category", category);
		
		// 페이징처리용 map
		Map<String, Object> pagingmap = new HashMap<String, Object>();
		pagingmap.put("category", category);
		
		if (!title.isEmpty()) {
			map.put("title", title);
			pagingmap.put("title", title);
			
			//performanceForm.setTitle(title);
		}
		if (searchGenres != null && searchGenres.length > 0 && !"전체".equals(searchGenres[0])) {
			map.put("genres", searchGenres);
			pagingmap.put("genres", searchGenres);			
			//performanceForm.setGenres(searchGenres);
		}
		if (!startDay.isEmpty()) {
			map.put("startDate", startDay);			
			pagingmap.put("startDate", startDay);			
			//performanceForm.setStartDay(startDay);
		}
		if (!endDay.isEmpty()) {
			map.put("endDate", endDay);			
			pagingmap.put("endDate", endDay);			
			//performanceForm.setEndDay(endDay);
		}
		if (!"0".equals(age)) {
			map.put("age", age);
			pagingmap.put("age", age);
			//performanceForm.setAge(age);
		}
		
		pagingmap.put("pageNo", pageNo);
		pagingmap.put("rows", rows);		
		
		System.out.println(map);		
		System.out.println("pagingmap: "+ pagingmap);		
		List<PerformanceDetailDto> performances = performanceService.searchPerformances(map);
		Map<String, Object> resultMap = performanceService.getPerformanceForPaging(pagingmap);
		
		String genreCat = "";
		if (category.equals("콘서트")) {
			genreCat = "콘서트";
		} else if(category.equals("뮤지컬") || category.equals("연극")) {
			genreCat = "뮤지컬/연극";
		}			
		
		String[] genres = performanceService.getGenreByCategory(genreCat);	
		
		model.addAttribute("performances", performances);
		model.addAttribute("category", category);
		model.addAttribute("genres", genres );
		//model.addAttribute("performanceForm", performanceForm);
					
		return "/performance/list";
	}
	
	
	
	
	@GetMapping("/detail.do")
	public @ResponseBody PerformanceDetailDto getProduct(@RequestParam("id") int performanceId) {
		PerformanceDetailDto performance = performanceService.getPerformanceDetailById(performanceId);
		//System.out.println("detail옴");
		
		return performance;
	}
	
}
