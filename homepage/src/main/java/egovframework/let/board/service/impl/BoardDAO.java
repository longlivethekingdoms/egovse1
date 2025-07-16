package egovframework.let.board.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.let.board.service.BoardVO;
import egovframework.let.temp2.service.Temp2VO;

@Repository("boardDAO")
public class BoardDAO extends EgovAbstractMapper {
	
	//게시물 목록 가져오기
	public List<EgovMap> selectBoardList(BoardVO vo) throws Exception{
		return selectList ("boardDAO.selectBoardList", vo);
	}
	
	//게시물 수
	public int selectBoardListCnt(BoardVO vo) throws Exception{
		return selectOne("boardDAO.selectBoardListCnt", vo);
	}
	
	public void updateViewCnt(BoardVO vo) throws Exception{
		update("boardDAO.updateViewCnt", vo);
	}
	
	public BoardVO selectBoard(BoardVO vo) throws Exception{
		return selectOne("boardDAO.selectBoard", vo);
	}
	
	public void insertBoard(BoardVO vo) throws Exception{
		insert("boardDAO.insertBoard", vo);
	}
	
	public void updateBoard(BoardVO vo) throws Exception{
		update("boardDAO.updateBoard", vo);
	}
	
	public void deleteBoard(BoardVO vo) throws Exception{
		delete("boardDAO.deleteBoard", vo);
	}
}
