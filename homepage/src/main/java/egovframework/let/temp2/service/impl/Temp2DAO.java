package egovframework.let.temp2.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.let.temp2.service.Temp2VO;

@Repository("temp2DAO")
public class Temp2DAO extends EgovAbstractMapper {
	
	//임시데이터 등록
	public void insertTemp(Temp2VO vo) throws Exception{
		insert("temp2DAO.insertTemp", vo);
	}
	
	//임시데이터 가져오기
	public Temp2VO selectTemp(Temp2VO vo) throws Exception{
		return selectOne("temp2DAO.selectTemp", vo);
	}
	
	//임시데이터 목록 가져오기
	public List<EgovMap> selectTempList(Temp2VO vo) throws Exception{
		return selectList("temp2DAO.selectTempList", vo);
	}
	
	//임시데이터 목록 수
	public int selectTempListCnt(Temp2VO vo) throws Exception{
		return selectOne("temp2DAO.selectTempListCnt", vo);
	}
	
	//임시데이터 수정
	public void updateTemp(Temp2VO vo) throws Exception{
		update("temp2DAO.updateTemp", vo);
	}
	
	//임시데이터 삭제
	public void deleteTemp(Temp2VO vo) throws Exception{
		delete("temp2DAO.deleteTemp", vo);
	}
}
