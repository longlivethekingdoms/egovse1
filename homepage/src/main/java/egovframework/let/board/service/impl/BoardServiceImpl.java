package egovframework.let.board.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import egovframework.let.board.service.BoardService;
import egovframework.let.board.service.BoardVO;

@Service("boardService")
public class BoardServiceImpl extends EgovAbstractServiceImpl implements BoardService{
	
	@Resource(name="boardDAO")
	private BoardDAO boardDAO;
	
	@Resource(name = "egovBoardIdGnrService")
	private EgovIdGnrService idgenService;
	
	//게시물 목록 가져오기
	public List<EgovMap> selectBoardList(BoardVO vo) throws Exception{
		return boardDAO.selectBoardList(vo);
	}
	
	public int selectBoardListCnt(BoardVO vo) throws Exception {
		return boardDAO.selectBoardListCnt(vo);
	}
	
	public BoardVO selectBoard(BoardVO vo) throws Exception{
		boardDAO.updateViewCnt(vo);
		return boardDAO.selectBoard(vo);
	}
	
	public String insertBoard(BoardVO vo) throws Exception{
		String id = idgenService.getNextStringId();
		vo.setBoardId(id);
		boardDAO.insertBoard(vo);
		
		return id;
	}
	
	public void updateBoard(BoardVO vo) throws Exception{
		boardDAO.updateBoard(vo);
	}
	
	public void deleteBoard(BoardVO vo) throws Exception{
		boardDAO.deleteBoard(vo);
	}
}
