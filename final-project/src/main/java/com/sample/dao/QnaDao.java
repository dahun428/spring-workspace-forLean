package com.sample.dao;

import java.util.List;
import java.util.Map;

import com.sample.web.view.Qna;

public interface QnaDao {
	
    void updateQna(Qna qna);
    Qna getQnaDetail(int qnaId);
    List<Qna> getAllQnas();
    List<Qna> getQnasByCategory(String category);
    List<Qna> getQnasByUserId(String userId);
    
    void insertQna(Qna qna);
	int getTotalRowsOfQnaList(Map<String, String> map);
	List<Qna> getQnas(Map<String, Object> map);
}