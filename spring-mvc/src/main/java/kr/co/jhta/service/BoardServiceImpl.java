package kr.co.jhta.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.jhta.dao.BoardDao;
import kr.co.jhta.vo.Board;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{

	@Autowired
	BoardDao boardDao;
	
	public void addNewBoard(Board board) {
		boardDao.insertBoard(board);
	}
	public void deleteBoard(long boardNo, String password) {
		Board savedBoard = boardDao.selectBoard(boardNo);

		if(savedBoard == null) {
			throw new RuntimeException("["+boardNo+"]번 게시글이 없습니다.");
		}
		if(!password.equals(savedBoard.getPassword())) {
			throw new RuntimeException("게시글의 비밀번호가 일치하지 않습니다.");
		}
		savedBoard.setDeleted("Y");
		savedBoard.setDeletedDate(new Date());
		boardDao.updateBoard(savedBoard);
	}
	public List<Board> getAllBoards() {
		return boardDao.selectBoards();
	}
	public Board getBoardDetail(long boardNo) {
		return boardDao.selectBoard(boardNo);
	}
	public void increaseBoardLikes(long boardNo) {
		Board board = boardDao.selectBoard(boardNo);
		board.setLikes(board.getLikes()+1);
		board.setUpdatedDate(new Date());
		boardDao.updateBoard(board);
	}
	public void modifyBoardDetail(Board board) {
		Board savedBoard = boardDao.selectBoard(board.getNo());
		BoardCheckFuncForNullOrDiffPasswordIncreaseException(savedBoard);
		savedBoard.setTitle(board.getTitle());
		savedBoard.setContent(board.getContent());
		savedBoard.setFilename(board.getFilename());
		savedBoard.setDeleted(board.getDeleted());
		savedBoard.setUpdatedDate(board.getUpdatedDate());
		savedBoard.setDeletedDate(board.getDeletedDate());
		boardDao.updateBoard(savedBoard);
	}
	private void BoardCheckFuncForNullOrDiffPasswordIncreaseException(Board board) {
		Board savedBoard = boardDao.selectBoard(board.getNo());
		if(savedBoard == null) {
			throw new RuntimeException("["+board.getNo()+"]번 게시글이 없습니다.");
		}
		if(!savedBoard.getPassword().equals(board.getPassword())) {
			throw new RuntimeException("게시글의 비밀번호가 일치하지 않습니다.");
		}
	}
}
