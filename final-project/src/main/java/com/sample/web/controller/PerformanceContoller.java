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

import org.springframework.beans.BeanUtils;
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

import com.sample.dao.PerformanceDao;
import com.sample.dto.PerformanceDetailDto;
import com.sample.service.HallService;
import com.sample.service.PerformanceService;
import com.sample.web.form.PerformanceForm;
import com.sample.web.form.PerformanceUpdateForm;
import com.sample.web.view.HallInfo;
import com.sample.web.view.Pagination;
import com.sample.web.view.Performance;
import com.sample.web.view.PerformanceMain;
import com.sample.web.view.PerformanceSeatPrice;




@Controller
@RequestMapping("/performance")
@SessionAttributes("performanceForm")
public class PerformanceContoller {

	@Autowired
	private PerformanceService performanceService;
	
	@Autowired
	private HallService hallService;
	
	private String saveDirectory="C:/APP/eGovFrameDev-3.9.0-64bit/workspace/final-project/src/main/webapp/resources/sample-images";
		
	
	@GetMapping("/update/main.do")
	public String updateMain(@RequestParam("performanceId") int performanceId, 
			@RequestParam("category") String category, Model model) {		
		
		return "/performance/update/main";
	}
	
	@GetMapping("/update/cancel.do")
	public String cancelUpdate(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/home.do";	// 나중엔 performance/list.do?category='category'로 변경
	}
	
	
	@GetMapping("/update/updateBasicInfo.do")
	public String updateBasic(@RequestParam("performanceId") int performanceId, 
			@RequestParam("category") String category, Model model) {
		
		System.out.println("여기는 updateBasicInfo");
		
		System.out.println("category: "+category);
		System.out.println("performanceId: "+performanceId);
		
		String genreCat = "";
		if (category.equals("콘서트")) {
			genreCat = "콘서트";
		} else if(category.equals("뮤지컬") || category.equals("연극")) {
			genreCat = "뮤지컬/연극";
		}			
		
		String[] genres = performanceService.getGenreByCategory(genreCat);		
		
		PerformanceDetailDto performance = performanceService.getPerformanceDetailById(performanceId);
		//PerformanceForm performanceForm = new PerformanceForm();
		
		model.addAttribute("category",category);
		model.addAttribute("genres", genres);
		model.addAttribute("performanceId",performanceId);
		model.addAttribute("performance", performance);	
		
		return "/performance/update/updateBasicInfo";
	}
	
	@PostMapping("/update/updateBasicInfo.do")
	public String updateBasicSubmit(PerformanceUpdateForm performanceUpdateForm,
			Model model) throws Exception{
		
		System.out.println("여기는 uddateBasicInfo를 submit한 폼입니다.");
		System.out.println("id: "+performanceUpdateForm.getId());
		System.out.println("category: "+performanceUpdateForm.getCategory());
		System.out.println("title: "+performanceUpdateForm.getTitle());
		System.out.println("genre: "+performanceUpdateForm.getGenre().toString());
		System.out.println("startDate: "+performanceUpdateForm.getStartDate());
		System.out.println("endDate: "+performanceUpdateForm.getEndDate());
		System.out.println("rating: "+performanceUpdateForm.getRating());		
		System.out.println("runningTime: "+performanceUpdateForm.getRunningTime());
		System.out.println("provider: "+performanceUpdateForm.getProvider());
		System.out.println("upfile: "+performanceUpdateForm.getUpfile().getOriginalFilename());
		System.out.println("explain: "+performanceUpdateForm.getExplain());
		System.out.println("imageSelect: " + performanceUpdateForm.getImageSelect());
		System.out.println("upfile: " + performanceUpdateForm.getUpfile().getOriginalFilename());
		
		// 장르 설정하기
		List<String> genres = new ArrayList<String>();
		for (String genre : performanceUpdateForm.getGenre()) {
			genres.add(genre);
		}			
		
		Performance performance = new Performance();
		
		BeanUtils.copyProperties(performanceUpdateForm, performance);
		performance.setGenre(genres);
		
		System.out.println("id: "+performance.getId());
		System.out.println("category: "+performance.getCategory());
		System.out.println("title: "+performance.getTitle());
		System.out.println("genre: "+performance.getGenre().toString());
		System.out.println("startDate: "+performance.getStartDate());
		System.out.println("endDate: "+performance.getEndDate());
		System.out.println("rating: "+performance.getRating());		
		System.out.println("runningTime: "+performance.getRunningTime());
		System.out.println("provider: "+performance.getProvider());
		
		if ("existing".equals(performanceUpdateForm.getImageSelect())) {
			System.out.println("기존의 이미지");
								
			// 기존의 이미지경로 가져오기 
			PerformanceDetailDto performanceDetailDto = performanceService.getPerformanceDetailById(performance.getId());
			performance.setImagePath(performanceDetailDto.getImagePath());
			
			System.out.println("imagePath: "+performance.getImagePath());
			System.out.println("explain: "+performance.getExplain());
			
			// performanceInfo 테이블 
			
			
		} else if ("new".equals(performanceUpdateForm.getImageSelect())) {
			System.out.println("새 이미지");
			// 새 이미지 저장하기 
			MultipartFile upFile = performanceUpdateForm.getUpfile();
			if (!upFile.isEmpty()) {
				String filename = upFile.getOriginalFilename();
				filename = System.currentTimeMillis()+filename;	
				File file = new File(saveDirectory, filename);
				FileCopyUtils.copy(upFile.getInputStream(), new FileOutputStream(file));	
				performance.setImagePath(filename);
//				performance.setImagePath(filename);		
			}
			
		}
		
		// 1. 기존의 장르테이블을 지우고 새로 입력한다.
		// 2. 공연정보 테이블에 새로운 정보들을 저장한다.
		performanceService.updatePerformanceInfo(performance);		
		
		
		model.addAttribute("category", performanceUpdateForm.getCategory());
		model.addAttribute("performanceId",performanceUpdateForm.getId());
		
		return "redirect:completed.do";
	}
	
	@GetMapping("/update/completed.do")
	public String updateCompleted(@RequestParam("performanceId") int performanceId, 
			@RequestParam("category") String category, Model model) {
		
		model.addAttribute("category", category);
		model.addAttribute("performanceId", performanceId);
		
		return "/performance/update/completed";
	}
	
	@GetMapping("/update/updateLocationInfo.do")
	public String updateLocation(@RequestParam("performanceId") int performanceId, 
			@RequestParam("category") String category, Model model) {
		
		System.out.println("여기는 updateLocationInfo");
		
		System.out.println("category: "+category);
		System.out.println("performanceId: "+performanceId);
				
		PerformanceDetailDto performance = performanceService.getPerformanceDetailById(performanceId);
		//PerformanceForm performanceForm = new PerformanceForm();
		
		List<HallInfo> hallInfos = performanceService.getAllHallInfos();
		HallInfo hallInfo = performanceService.getHallInfoByPerformanceInfoId(performanceId);
		
		
		model.addAttribute("hallInfos", hallInfos);
		
		
		model.addAttribute("category",category);		
		model.addAttribute("performanceId",performanceId);
		model.addAttribute("performance", performance);	
		model.addAttribute("hallInfo", hallInfo);
				
		return "/performance/update/updateLocationInfo";
	}
	
	@PostMapping("/update/updateLocationInfo.do")
	public String updateLocationSubmit(PerformanceUpdateForm performanceUpdateForm,
			Model model) {
		
		System.out.println("이곳은 updateLocationInfo를 submit한 폼입니다.!");
				
		System.out.println("id: "+performanceUpdateForm.getId());
		System.out.println("category: "+performanceUpdateForm.getCategory());
		System.out.println("hallInputType: "+performanceUpdateForm.getHallInputType());
		System.out.println("originalHallId: " + performanceUpdateForm.getOriginalHallId());
		System.out.println("hallName: "+performanceUpdateForm.getHallName());
		System.out.println("hallAddress: "+performanceUpdateForm.getHallAddress());
		
		Performance performance = new Performance();		
		BeanUtils.copyProperties(performanceUpdateForm, performance);
			
		HallInfo hallInfo = performanceService.getHallInfoById(performanceUpdateForm.getOriginalHallId());
		performance.setHallInfo(hallInfo);
		
		System.out.println("performanceId: "+performance.getId());
		System.out.println("performanceInfoId: "+performance.getHallInfo().getId());
		
		
		if ("new".equals(performanceUpdateForm.getHallInputType())) {			
			if((performanceUpdateForm.getHallName().equals(hallInfo.getName()))
				&& (performanceUpdateForm.getHallAddress().equals(hallInfo.getAddress()))) {
				// 직접입력이고 기존 정보와 차이가 없을때 (수정을 하지 않음)
				// 아무일도 하지 않는다.
				System.out.println("기존정보와 동일함");
				
			} else {
				// 직접입력이고 새로 데이터를 입력할 때
				// 1. 새로운 공연장 정보를 데이터베이스에 저장한다.
				// 2. 공연정보와 연결된 공연장 정보를 바꿔준다.
				HallInfo newHallInfo = new HallInfo();
				newHallInfo.setName(performanceUpdateForm.getHallName());
				newHallInfo.setAddress(performanceUpdateForm.getHallAddress());
				newHallInfo.setGpsX(performanceUpdateForm.getGpsX());
				newHallInfo.setGpsY(performanceUpdateForm.getGpsY());
				String insertHallInfo = "Y";	// 새로 입력한 공연정보이다.
				System.out.println("new HallInfo");
				System.out.println("hallName: "+newHallInfo.getName());
				System.out.println("hallAddress: "+newHallInfo.getAddress());
				System.out.println("gpsX: "+newHallInfo.getGpsX());
				System.out.println("gpsY: "+newHallInfo.getGpsY());
				
				performanceService.updatePerformanceHallInfo(performance, newHallInfo, insertHallInfo);
			}
					
			
		} else if ("existing".equals(performanceUpdateForm.getHallInputType())) {
			if (performanceUpdateForm.getHallId() == performanceUpdateForm.getOriginalHallId()) {
				// 기존 공연장 입력이고 기존 정보와 차이가 없을때(수정을 하지 않음)
				// 아무일도 하지 않는다.
				System.out.println("기존정보와 동일함");
			}else  {
				// 공연정보와 연결된 공연장정보를 바꿔준다.
				// 기존의 공연장정보를 가져온후 그 공연장정보로 공연정보의 공연장정보를 바꿔준다.
				System.out.println("새로운 공연정보 아이디: "+ performanceUpdateForm.getHallId());
				HallInfo newHallInfo = performanceService.getHallInfoById(performanceUpdateForm.getHallId());
				System.out.println("new HallInfo");
				System.out.println("hallInfoId: "+newHallInfo.getId());
				String insertHallInfo = "N";	// 기존의 공연정보이다. 
				
				performanceService.updatePerformanceHallInfo(performance, newHallInfo, insertHallInfo);
			}
		}
		
		
		model.addAttribute("category", performanceUpdateForm.getCategory());
		model.addAttribute("performanceId",performanceUpdateForm.getId());
		
		return "redirect:completed.do";
	}
	
	
	
	@GetMapping("/update/updateSeatInfo.do")
	public String updateSeat(@RequestParam("performanceId") int performanceId, 
			@RequestParam("category") String category, Model model) {
		
		System.out.println("여기는 updateLocationInfo");
		System.out.println("category: "+category);
		System.out.println("performanceId: "+performanceId);
				
		PerformanceDetailDto performance = performanceService.getPerformanceDetailById(performanceId);
		
		PerformanceSeatPrice aSeat = new PerformanceSeatPrice();
		aSeat.setInfoId(performanceId);
		aSeat.setSeatRate("A");
		
		PerformanceSeatPrice sSeat = new PerformanceSeatPrice();
		sSeat.setInfoId(performanceId);
		sSeat.setSeatRate("S");
		
		PerformanceSeatPrice rSeat = new PerformanceSeatPrice();
		rSeat.setInfoId(performanceId);
		rSeat.setSeatRate("R");
		
		PerformanceSeatPrice aSeatInfo = performanceService.getPerformanceSeatPriceByPerformanceInfoAndSeatRate(aSeat);
		PerformanceSeatPrice sSeatInfo = performanceService.getPerformanceSeatPriceByPerformanceInfoAndSeatRate(sSeat);
		PerformanceSeatPrice rSeatInfo = performanceService.getPerformanceSeatPriceByPerformanceInfoAndSeatRate(rSeat);
		
		model.addAttribute("category",category);		
		model.addAttribute("performanceId",performanceId);
		model.addAttribute("performance", performance);	
		model.addAttribute("aSeat", aSeatInfo);
		model.addAttribute("sSeat", sSeatInfo);
		model.addAttribute("rSeat", rSeatInfo);
		
		
		return "/performance/update/updateSeatInfo";
	}
	
	@PostMapping("/update/updateSeatInfo.do")
	public String updateSeatSubmit(PerformanceUpdateForm performanceUpdateForm,
			Model model) {
		
		System.out.println("여기는 updateLocationInfo를 submit한 폼입니다.");
		
		System.out.println("여기는 updateLocationInfo");
		System.out.println("id: "+performanceUpdateForm.getId());
		System.out.println("category: "+performanceUpdateForm.getCategory());
		System.out.println("aPrice: " + performanceUpdateForm.getaPrice());
		System.out.println("sPrice: " + performanceUpdateForm.getsPrice());
		System.out.println("rPrice: " + performanceUpdateForm.getrPrice());
		
		Performance performance = new Performance();		
		BeanUtils.copyProperties(performanceUpdateForm, performance);
		
		System.out.println("performanceId: "+performance.getId());
		
		// 새 좌석정보를 Map에 저장한다.
		Map<String, Integer> map = new HashMap<>();
		map.put("A", performanceUpdateForm.getaPrice());
		map.put("S", performanceUpdateForm.getsPrice());
		map.put("R", performanceUpdateForm.getrPrice());
		
		performanceService.updatePerformanceSeatInfo(performance, map);
		
		
		model.addAttribute("category", performanceUpdateForm.getCategory());
		model.addAttribute("performanceId",performanceUpdateForm.getId());
		
		return "redirect:completed.do";
		
	}
	
	
	
	@GetMapping("delete.do")
	public String delete(@RequestParam("performanceId") int performanceId,
			@RequestParam("category") String category, Model model) {
		
		System.out.println("여기는 delete.do 입니다.");
		System.out.println("performanceId: "+performanceId );
		//return "redirect:/home.do";
		
		PerformanceDetailDto performance =  performanceService.getPerformanceDetailById(performanceId);
		
		if (performance == null) {
			model.addAttribute("error", "notFound");
		} else {
			// 공연메인정보 조회
			List<PerformanceMain> performanceMain = performanceService.getPerformanceMain(performanceId);
			// 공연장르 조회
			String[] performanceGenres = performanceService.getGenreById(performanceId);
			// 공연 좌석가격정보 조회
			List<PerformanceSeatPrice> seatPrices = performanceService.getPerformanceSeatPriceById(performanceId);
			
			System.out.println("메인정보 길이: "+performanceMain.size());
			System.out.println("공연장르 길이: "+performanceGenres.length);
			System.out.println("공연좌석 정보 길이 조회: "+seatPrices.size());
			
			// 이미 해당공연에 예약을 한 사람이 있는 경우 삭제를 진행할 수 없다.
			// performance_main 테이블에 performanceInfoId에 해당하는 행이 있는 경우이다.
			if (performanceMain.size() != 0) {
				model.addAttribute("error", "disable");
			} else {
				// 공연정보를 삭제한다.
				performanceService.deletePerformance(performanceId);				
			}
			
		}		
		
		model.addAttribute("category", category);	
		
		return "/performance/delete";
	}
	
	
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
		System.out.println("imagepath: "+performanceForm.getImagePath());
			
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
		} else {
			String downloadFile = performanceService.saveImage(performanceForm.getImagePath(), performanceForm.getTitle(), saveDirectory);
			System.out.println("downloadFile : " + downloadFile);
			performanceForm.setImagePath(downloadFile);
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
		performance.setRunningTime(performanceForm.getRunningTime());
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
	
	@GetMapping("/totalList.do")
	public String totalList(Model model
			, @RequestParam("title") String title
			, @RequestParam(value = "pageNo", defaultValue="1") int pageNo
			, @RequestParam(value = "rows", defaultValue = "5") int rows
			, @RequestParam(value= "order", defaultValue = "dateOrder") String listOrder) {
		
			System.out.println("여기는 totalList입니다.");
			System.out.println("title: " + title);
			System.out.println("pageNo: " +pageNo);
			System.out.println("rows: " + rows);
			System.out.println("order: " + listOrder);
		
			Map<String, Object> pagingmap = new HashMap<String, Object>();
						
			pagingmap.put("title", title);
			pagingmap.put("pageNo", pageNo);
			pagingmap.put("rows", rows);	
			
			System.out.println("pagingmap: "+pagingmap);
			
			// 페이징 처리된 map을 조회한다.(검색조건에 해당하고, 특정 페이지 범위내의 자료를 가져온다.
			Map<String, Object> resultMap = performanceService.getTotalSearchForPaging(pagingmap);
			
			System.out.println("totalList를 컨트롤러에서 페이징처리한 결과");
			
			Pagination pagination = (Pagination) resultMap.get("pagination");
			
			List<PerformanceDetailDto> performancesWithPaging 
				= (List<PerformanceDetailDto>) resultMap.get("performances");
			
			int totalRows = (Integer)resultMap.get("totalRows");
			int totalPageCount = (int)Math.ceil(((double)totalRows/(double)rows));
			
			System.out.println("totalRows: "+totalRows);
			System.out.println("totalPageCount: "+totalPageCount);
			
			model.addAttribute("title", title);
			model.addAttribute("performances", performancesWithPaging);
			model.addAttribute("pagination", pagination);
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("totalPageCount", totalPageCount);
			model.addAttribute("rows", rows);
		
		return "performance/totalList";
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
	public @ResponseBody Map<String, Object> getProduct(@RequestParam("id") int performanceId) {
		PerformanceDetailDto performance = performanceService.getPerformanceDetailById(performanceId);
		HallInfo hallInfo = hallService.getHallInfoByPerformanceId(performanceId);
		Map<String, Object> map = new HashMap<>();
		map.put("performance", performance);
		map.put("hallInfo", hallInfo);
		System.out.println("detail옴");
		
		return map;
	}
	
	@GetMapping("/add/showHallInfo.do")
	public @ResponseBody HallInfo getHallInfoById(@RequestParam("hallId") int hallId) {				
		return performanceService.getHallInfoById(hallId);
	}
	
	@GetMapping("/update/getGenres.do")
	public @ResponseBody String[] getGenresByCategory(@RequestParam("category") String category) {
		
		System.out.println(category);
		
		String genreCat = "";
		if (category.equals("콘서트")) {
			genreCat = "콘서트";
		} else if(category.equals("뮤지컬") || category.equals("연극")) {
			genreCat = "뮤지컬/연극";
		}
				
		//Map<String, Object> map = new HashMap<>();
		//map.put("categoryGenres", performanceService.getGenreByCategory(genreCat));
		
		String[] genres = performanceService.getGenreByCategory(genreCat);
		
		//System.out.println(genres[0]);
		
		return genres;
	}
	
	
	
}
