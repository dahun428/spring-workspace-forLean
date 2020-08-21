package com.sample.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.dao.PerformanceDao;
import com.sample.dto.PerformanceDetailDto;
import com.sample.dto.PerformanceDto;
import com.sample.web.view.HallInfo;
import com.sample.web.view.Pagination;
import com.sample.web.view.Performance;
import com.sample.web.view.PerformanceSchedule;
import com.sample.web.view.PerformanceSeatPrice;

@Service
@Transactional
public class PerformanceServiceImpl implements PerformanceService {
	
	@Autowired
	PerformanceDao performanceDao;

	@Override
	public void addPerformance(Performance performance) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PerformanceDetailDto> getPerformancesByCategory(String category) {
		// TODO Auto-generated method stub
		
		return performanceDao.getPerformancesByCategory(category);
	}

	@Override
	public PerformanceDetailDto getPerformanceDetailById(int performanceId) {
		// TODO Auto-generated method stub
		return performanceDao.getPerformanceById(performanceId);
	}
	
	@Override
	public PerformanceDto getPerformanceDetail(int performanceId) {
		return performanceDao.selectPerformance(performanceId);
	}

	@Override
	public List<PerformanceSchedule> getPerformanceDetailByMap(Map<String, Object> data) {
		return performanceDao.selectPerformanceByIdAndDate(data); 
	}

	/**
	 * performanceId로 performanceDetailDto 객체를 획득할 수 있다.
	 */
	public PerformanceDetailDto getPerformanceByPerformanceMainId(int performanceMainId) {
		return performanceDao.getPerformanceByPerformanceMainId(performanceMainId);
	}
	@Override
	public List<Performance> getAllPerformances() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Performance> searchPerformances(Performance performance) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String[] getGenreByCategory(String category) {
		// TODO Auto-generated method stub
		return performanceDao.getGenreByCategory(category);
	}

	@Override
	public List<PerformanceDetailDto> searchPerformances(Map<String, Object> map) {
		// TODO Auto-generated method stub
		// 줄 출력 테스트
		System.out.println("검색조건 퍼포먼스 총 줄 수:" + performanceDao.getTotalRowsOfPerformance(map));
		return performanceDao.searchPerformance(map);
	}
	


	@Override
	public List<PerformanceSeatPrice> getPerformanceSeatPriceById(int performanceId) {
		return performanceDao.getPerformanceSeatPrice(performanceId);
	}

	@Override
	public List<PerformanceSchedule> getPerformanceDetailByDate(String performanceDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerformanceSchedule getPerformanceScheduleByPerformanceId(int performanceMainId) {
		return performanceDao.getScheduleByPerformanceMainId(performanceMainId);
	}

	@Override
	public List<PerformanceDto> getPerformanceByUserId(String userId) {
		return performanceDao.getPerformanceByUserId(userId);
	}

	@Override
	public Map<String, Object> getPerformanceForPaging(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		System.out.println("페이징 처리를 위한 getPerformanceForPaging");
		
		System.out.println("pageNo: "+map.get("pageNo"));
		System.out.println("rows: "+map.get("rows"));
		
		int pageNo = (int) map.get("pageNo");
		int rows = (int) map.get("rows");
		
		// 검색조건에 해당하는 총 공연갯수
		int totalRows = performanceDao.getTotalRowsOfPerformance(map);
		System.out.println("totalRows: "+ totalRows);
		
		// 페이지 네비게이션에 필요한 정보 생성하기
		// 한 화면에 보여줄 행의 갯수, 블록당 페이지번호 수, 현재 페이지 번호, 총 행의 갯수
		Pagination pagination = new Pagination(rows, 5, pageNo, totalRows);
		// 현재 페이지번호에 해당하는 데이터 조회에 필요한 구간계산하기
		int beginIndex = (pageNo - 1)*rows + 1;
		if (beginIndex == 1) {
			beginIndex = 0;
		}
		int endIndex = rows;
		
		// 조회조건을 만족하는 공연 조회하기
		map.put("beginIndex", beginIndex);
		map.put("endIndex", endIndex);		
		
		System.out.println("기존 map에 추가됨");
		List<PerformanceDetailDto> performances = performanceDao.searchPerformanceByPage(map);
		
		// 테스트용
		System.out.println("검색조건과 범위대로 조회되었나 체크합니다.");
		for (PerformanceDetailDto p : performances) {
			System.out.println(p.getTitle());
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("performances", performances);
		result.put("pagination", pagination);
		result.put("totalRows", totalRows);
		
		return result;
	}

	@Override
	public List<HallInfo> getAllHallInfos() {
		// TODO Auto-generated method stub
		return performanceDao.getAllHallInfos();
	}

	@Override
	public HallInfo getHallInfoById(int HallInfoid) {
		// TODO Auto-generated method stub
		return performanceDao.getHallInfoById(HallInfoid);
	}
	
	

}
