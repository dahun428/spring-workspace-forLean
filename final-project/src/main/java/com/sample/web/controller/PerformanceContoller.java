package com.sample.web.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import com.sample.dto.PerformanceDetailDto;
import com.sample.service.PerformanceService;
import com.sample.web.form.PerformanceForm;
import com.sample.web.view.HallInfo;
import com.sample.web.view.Pagination;
import com.sample.web.view.Performance;
import com.sample.web.view.PerformanceSeatPrice;





@Controller
@RequestMapping("/performance")
@SessionAttributes("performanceForm")
public class PerformanceContoller {

	@Autowired
	private PerformanceService performanceService;
	
	private String saveDirectory="C:/APP/eGovFrameDev-3.9.0-64bit/workspace/final-project/src/main/webapp/resources/sample-images";
	
	@GetMapping("/add/step1.do")
	public String addStep1(Model model, @RequestParam(value="category", defaultValue="") String category) {
		
		String genreCat = "";
		if (category.equals("콘서트")) {
			genreCat = "콘서트";
		} else if(category.equals("뮤지컬") || category.equals("연극")) {
			genreCat = "뮤지컬/연극";
		}			
		
		String[] genres = performanceService.getGenreByCategory(genreCat);			
		
		model.addAttribute("category",category);
		model.addAttribute("genres", genres);
		
		PerformanceForm performanceForm = new PerformanceForm();
		model.addAttribute("performanceForm", performanceForm);
		
		return "/performance/add/step1";
	}
	
	
	@PostMapping("/add/step1.do")
	public String addStep1Submit(@ModelAttribute("performanceForm") PerformanceForm performanceForm ) throws Exception {
		
		System.out.println("왔다");
		System.out.println("이곳은 step1 submit 이후입니다.");
		
		System.out.println("category: "+performanceForm.getCategory());
		System.out.println("title: "+performanceForm.getTitle());
		System.out.println("startDate: "+performanceForm.getStartDate());
		System.out.println("endDate: "+performanceForm.getEndDate());
		System.out.println("rating: "+performanceForm.getRating());
		System.out.println("runningTime: "+performanceForm.getRunningTime());
		System.out.println("provider: "+performanceForm.getProvider());
		System.out.println("upfile: "+performanceForm.getUpfile().getOriginalFilename());
		System.out.println("explain: "+performanceForm.getExplain());				
		//System.out.println("category: "+performanceForm.getGenre().toString());
		System.out.println("hallInputType: " + performanceForm.getHallInputType() );
		System.out.println("hallInputType: " + performanceForm.getHallId() );
		System.out.println("hallInputType: " + performanceForm.getHallName() );
		System.out.println("hallInputType: " + performanceForm.getHallAddress() );
		
		
		MultipartFile upFile = performanceForm.getUpfile();
		if (!upFile.isEmpty()) {
			String filename = upFile.getOriginalFilename();
			filename = System.currentTimeMillis()+filename;	
			File file = new File(saveDirectory, filename);
			FileCopyUtils.copy(upFile.getInputStream(), new FileOutputStream(file));	
			performanceForm.setImagePath(filename);
//			performance.setImagePath(filename);		
		}
		
		
		
		System.out.println("genre");
		for (String g : performanceForm.getGenre()) {
			System.out.print(g + " ");
		}
		
		return "redirect:step2.do";
	}
	
	@GetMapping("/add/step2.do") 
	public String addStep2(Model model, @ModelAttribute("performanceForm") PerformanceForm performanceForm) {
		
		List<HallInfo> hallInfos = performanceService.getAllHallInfos();
		
		model.addAttribute("hallInfos", hallInfos);
		return "/performance/add/step2";
		
	}
	
	@PostMapping("/add/step2.do")
	public String addStep2Submit(@ModelAttribute("performanceForm") PerformanceForm performanceForm ) {
		
		System.out.println("이곳은 step2 submit 이후입니다.");
		System.out.println("category: "+performanceForm.getCategory());
		System.out.println("title: "+performanceForm.getTitle());
		System.out.println("startDate: "+performanceForm.getStartDate());
		System.out.println("endDate: "+performanceForm.getEndDate());
		System.out.println("rating: "+performanceForm.getRating());
		System.out.println("runningTime: "+performanceForm.getRunningTime());
		System.out.println("provider: "+performanceForm.getProvider());
		System.out.println("upfile: "+performanceForm.getUpfile().getOriginalFilename());
		System.out.println("explain: "+performanceForm.getExplain());				
		System.out.println("hallInputType: " + performanceForm.getHallInputType() );
		System.out.println("hallId: " + performanceForm.getHallId() );
		System.out.println("hallName: " + performanceForm.getHallName() );
		System.out.println("hallAddress: " + performanceForm.getHallAddress() );
		System.out.println("gpsX: " + performanceForm.getGpsX());
		System.out.println("gpsY: " + performanceForm.getGpsY());
		
		
		
		return "redirect:step3.do";
	}
	
	@GetMapping("/add/step3.do")
	public String addStep3(Model model, @ModelAttribute("performanceForm") PerformanceForm performanceForm) {
					
		return "/performance/add/step3";
	}
	
	@PostMapping("/add/step3.do")
	public String addStep3Submit(@ModelAttribute("performanceForm") PerformanceForm performanceForm,
			SessionStatus sessionStatus, Model model) throws Exception{
		
		// 미완성
		
		System.out.println("이곳은 step3 submit 이후입니다.");
		System.out.println("category: "+performanceForm.getCategory());
		System.out.println("title: "+performanceForm.getTitle());
		System.out.println("startDate: "+performanceForm.getStartDate());
		System.out.println("endDate: "+performanceForm.getEndDate());
		System.out.println("rating: "+performanceForm.getRating());
		System.out.println("runningTime: "+performanceForm.getRunningTime());
		System.out.println("provider: "+performanceForm.getProvider());
		//System.out.println("upfile: "+performanceForm.getUpfile().getOriginalFilename());
		System.out.println("explain: "+performanceForm.getExplain());				
		System.out.println("hallInputType: " + performanceForm.getHallInputType() );
		System.out.println("hallId: " + performanceForm.getHallId() );
		System.out.println("hallName: " + performanceForm.getHallName() );
		System.out.println("hallAddress: " + performanceForm.getHallAddress() );
		System.out.println("gpsX: " + performanceForm.getGpsX());
		System.out.println("gpsY: " + performanceForm.getGpsY());
		
		System.out.println("aPrice: " + performanceForm.getaPrice());
		System.out.println("sPrice: " + performanceForm.getsPrice());
		System.out.println("rPrice: " + performanceForm.getrPrice());
		
		//Performance에 performanceForm 정보 담기
		Performance performance = new Performance();
		performance.setCategory(performanceForm.getCategory());
		performance.setTitle(performanceForm.getTitle());
		performance.setStartDate(performanceForm.getStartDate());
		performance.setEndDate(performanceForm.getEndDate());
		performance.setRating(performanceForm.getRating());
		performance.setRunnigTime(performanceForm.getRunningTime());
		performance.setProvider(performanceForm.getProvider());
		performance.setExplain(performanceForm.getExplain());
		performance.setImagePath(performanceForm.getImagePath());
		
		// 장르 넣기
		List<String> genres = new ArrayList<String>();
		for (String genre : performanceForm.getGenre()) {
			genres.add(genre);
		}		
		performance.setGenre(genres);
				
		// hallInfo 넣기 
		HallInfo hallInfo = new HallInfo();
		
		// 직접 입력의 경우
		// hallInfo.getId는 null이다.
		String insertHallYn = "";
		if("new".equals(performanceForm.getHallInputType())) {
			hallInfo.setName(performanceForm.getHallName());
			hallInfo.setAddress(performanceForm.getHallAddress());
			hallInfo.setGpsX(performanceForm.getGpsX());
			hallInfo.setGpsY(performanceForm.getGpsY());
			insertHallYn = "Y";
		} else if ("existing".equals(performanceForm.getHallInputType())) {	// 기존의 공연장을 선택한 경우			
			hallInfo = performanceService.getHallInfoById(performanceForm.getHallId());		
			insertHallYn = "N";
		}
		
		//PerformanceSeatPrice를 넣기 위한 Map
		Map<String, Integer> map = new HashMap<>();
		map.put("A", performanceForm.getaPrice());
		map.put("S", performanceForm.getsPrice());
		map.put("R", performanceForm.getrPrice());
		
		// 새 공연정보를 데이터베이스에 넣는다.
		performanceService.insertPerformance(performance, hallInfo, insertHallYn, map);
				
		// performanceForm 객체가 사라지기전에 category정보를 저장한다.
		model.addAttribute("category", performanceForm.getCategory());
		
		// @SessionAttribute({"이름", "이름})으로 모델의 저장된 객체 중에서 
		// 해당이름의 객체가 세션에 저장되게 되는데
		// sessionStatus.setComplete()메소드를 실행하면, 세션에 저장된 객체를
		// 삭제한다.
		
		sessionStatus.setComplete();
				
		return "redirect:completed.do";
		
	}
	
	@GetMapping("/add/completed.do")
	public String completed(Model model) {
		return "/performance/add/completed";
	}
	
	
	@GetMapping("/add/cancel.do")
	public String cancel(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/home.do";	// 나중엔 performance/list.do?category='category'로 변경
	}
	
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
			, @RequestParam(value= "age", defaultValue="0") String age
			, @RequestParam(value="changed", defaultValue="N", required=false) String changed) {
		
		
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
		
		//만약 폼이 변경된 뒤에 페이지번호나 검색버튼을 눌렀을 시
		if ("Y".equals(changed)) {
			pageNo = 1;
		}
		
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
		
		// 페이징 처리된 map을 조회한다.(검색조건에 해당하고, 특정 페이지 범위내의 자료를 가져온다.
		Map<String, Object> resultMap = performanceService.getPerformanceForPaging(pagingmap);
		
		System.out.println("Controller에서 페이징처리된 결과 테스트");
		Pagination pagination = (Pagination) resultMap.get("pagination");
		
		List<PerformanceDetailDto> performancesWithPaging 
			= (List<PerformanceDetailDto>) resultMap.get("performances");
		
		int totalRows = (Integer)resultMap.get("totalRows");
		int totalPageCount = (int)Math.ceil(((double)totalRows/(double)rows));
	
		// 전체 누르면 전체 12개의 줄이 나오는 오류
		System.out.println("totalRows: "+totalRows);
		System.out.println("totalPageCount: "+totalPageCount);
		
		String genreCat = "";
		if (category.equals("콘서트")) {
			genreCat = "콘서트";
		} else if(category.equals("뮤지컬") || category.equals("연극")) {
			genreCat = "뮤지컬/연극";
		}			
		
		String[] genres = performanceService.getGenreByCategory(genreCat);	
		
		model.addAttribute("category", category);
		model.addAttribute("genres", genres );
		// 페이징 처리가 안된 것
		//model.addAttribute("performances", performances);
		// 페이징 처리가 된 것
		model.addAttribute("performances", performancesWithPaging);
		model.addAttribute("pagination", pagination);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("rows", rows);
		
		//model.addAttribute("performanceForm", performanceForm);
		
	
		
		
		return "/performance/list";
	}
	
	
	
	
	@GetMapping("/detail.do")
	public @ResponseBody PerformanceDetailDto getProduct(@RequestParam("id") int performanceId) {
		PerformanceDetailDto performance = performanceService.getPerformanceDetailById(performanceId);
		System.out.println("detail옴");
		
		return performance;
	}
	
	@GetMapping("/add/showHallInfo.do")
	public @ResponseBody HallInfo getHallInfoById(@RequestParam("hallId") int hallId) {				
		return performanceService.getHallInfoById(hallId);
	}
	
}
