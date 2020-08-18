package com.sample.service;

import java.util.List;
import java.util.Map;

import com.sample.dto.MateDetailDto;
import com.sample.web.view.Mate;
import com.sample.web.view.MateTag;
import com.sample.web.view.MateTimeLine;
import com.sample.web.view.User;

public interface MateService {
    void addMate(Mate mate);
    List<Mate> getMatesByPerformanceId(int performanceId);
    void insertMateTimeLine(int mateId, MateTimeLine mateTimeLine);
    List<MateTag> addHashTag(int mateId, List<String> mateTags);
    void addMateMember(int mateId, User newMember);
    void changeCategory(int mateId, String category);
    
    /**
     * mate_main 테이블, mate_category 테이블에서
     * mate_category의 id, category(name) 값을 모두 가져온다.
     * @return List<Map<Integer, String>>
     */
    List<Map<Integer, String>> getMateAllCategory();
    /**
     * 메이트 방의 총 갯수를 가져오는 메소드
     * mate_main 테이블에서 performanceId 에 해당하는 테이블의 숫자를 가져온다.
     * @param performanceId
     * @return int (count)
     */
    Integer getCountMateByPerformanceId(int performanceId);

    /**
     * mateId에 해당하는 MateUser에 대한 모든 정보를 가져온다.
     * @param mateId
     * @return
     */
    List<User> getMateUserByMateId(int mateId);
    /**
     * mateId, performanceId 에 해당하는 메이트방의 상세 정보를 조회한다.
     * @param mateId
     * @param performanceId
     * @return
     */
    MateDetailDto getMateRoomDetail(int mateId, int performanceId);
    /**
     * mateTimeLine 남기는 기능
     * mateId로 해당 메이트 방을 제한하여 insert 한다.
     * userId로 누가 남겼는지 insert 한다.
     * @param mateId
     * @param userId
     */
    void addMateTimeLineByMateIdAndUserId(MateTimeLine mateTimeLine);
    
    /**
     * mateId에 해당하는 메이트 방의 타임라인의 갯수를 모두 불러온다.
     * @param mateId
     * @return
     */
    Map<String, Object> getMateTimeLineCountByMateId(int mateId);
    
    /**
     * categoryId와 mateId 를 받아서 해당 메이트 방의 카테고리를 변경한다.
     * @param categoryId
     * @param mateId
     */
    void updateMateCategoryByMateId(int categoryId, int mateId);
}