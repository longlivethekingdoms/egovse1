package egovframework.let.temp.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.let.temp.service.TempVO;

@Repository("tempDAO")
public class TempDAO extends EgovAbstractMapper {
	
	public TempVO selectTemp(TempVO vo) throws Exception{
		return selectOne("tempDAO.selectTemp", vo);
	}
	
	public List<EgovMap> selectTempList(TempVO vo) throws Exception{
		return selectList("tempDAO.selectTempList", vo);
	}
	
	public int selectTempListCnt(TempVO vo) throws Exception{
		return selectOne("tempDAO.selectTempListCnt", vo);
	}
	
	public void insertTemp(TempVO vo) throws Exception{
		insert("tempDAO.insertTemp", vo);
	}
	
	public void updateTemp(TempVO vo) throws Exception{
		update("tempDAO.updateTemp", vo);
	}
	
	public void deleteTemp(TempVO vo) throws Exception{
		delete("tempDAO.deleteTemp", vo);
	}
}
