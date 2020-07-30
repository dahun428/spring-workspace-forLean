package kr.co.jhta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.co.jhta.vo.Board;

public interface BoardService {

	List<Board> getAllBoards();
	void addNewBoard(Board board);
	Board getBoardDetail(long boardNo);
	void increaseBoardLikes(long boardNo);
	void modifyBoardDetail(Board board);
	void deleteBoard(long boardNo, String password);
	
}
