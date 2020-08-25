package com.sample.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.dao.HallDao;
import com.sample.dao.MateDao;
import com.sample.dao.PerformanceDao;
import com.sample.dto.JsonHallSeat;
import com.sample.web.form.MateForm;
import com.sample.web.view.HallInfo;
import com.sample.web.view.Mate;
import com.sample.web.view.Performance;
import com.sample.web.view.PerformanceSchedule;

@Service
public class MateManagerServiceImpl implements MateManagerService{


	@Autowired
	PerformanceDao performanceDao;

	@Autowired
	HallDao hallDao;

	@Autowired
	MateDao mateDao;

	@Transactional
	public void addMate(MateForm mateForm) {
		//performance_main insert

		PerformanceSchedule performance = new PerformanceSchedule();
		performance.setInfoId(mateForm.getPerformance().getInfoId());
		HallInfo hallinfo = new HallInfo();
		hallinfo.setId(mateForm.getPerformance().getHallId());
		performance.setHallinfo(hallinfo);
		performance.setShowDate(mateForm.getPerformance().getShowDate());
		performance.setShowTime(mateForm.getPerformance().getShowTime()+":00");
		performance.setShowNumber(mateForm.getPerformance().getShowNumber());
		//퍼포먼스 인서트하기
//		performanceDao.insertPerformanceSchedule(performance);
//		System.out.println("performance : "+performance);

		//mate Seat그룹 만들기
		List<JsonHallSeat> list = mateForm.getSeats();
		List<JsonHallSeat> distinctList = new ArrayList<>();
		distinctList.addAll(list.stream().collect(Collectors.toConcurrentMap(JsonHallSeat::getMateNo, Function.identity(), (p, q) -> p)).values());
		for(JsonHallSeat seat : distinctList) {
			System.out.println(seat);
		}
		int mateSize = distinctList.size();
		System.out.println("size : " + mateSize);
		
	}
		
}