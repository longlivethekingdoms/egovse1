package egovframework.let.board.service;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

public interface BoardService {
	
	public List<EgovMap> selectBoardList(BoardVO vo) throws Exception;
	
	//게시물 목록 수
	public int selectBoardListCnt(BoardVO vo) throws Exception;
	
	//게시물 상세 정보
	public BoardVO selectBoard(BoardVO vo) throws Exception;
	
	//public void updateViewCnt(BoardVO vo) throws Exception;
	
	//게시물 등록
	public String insertBoard(BoardVO vo) throws Exception;
	
	//게시물 수정
	public void updateBoard(BoardVO vo) throws Exception;
	
	public void deleteBoard(BoardVO vo) throws Exception;
}
