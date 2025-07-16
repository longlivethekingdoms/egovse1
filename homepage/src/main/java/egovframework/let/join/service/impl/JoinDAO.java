package egovframework.let.join.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.let.board.service.BoardVO;
import egovframework.let.join.service.JoinVO;
import egovframework.let.temp2.service.Temp2VO;

@Repository("joinDAO")
public class JoinDAO extends EgovAbstractMapper {
	
	//ID중복체크
	public int duplicateCheck(JoinVO vo) throws Exception{
		return selectOne("joinDAO.duplicateCheck", vo);
	}
	
	//회원등록
	public void insertJoin(JoinVO vo) throws Exception {
		insert("joinDAO.insertJoin", vo);
	}
	
}
