package egovframework.let.temp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import egovframework.let.temp.service.TempService;
import egovframework.let.temp.service.TempVO;

@Service("tempService")
public class TempServiceImpl extends EgovAbstractServiceImpl implements TempService{
	
	@Resource(name = "tempDAO")
	private TempDAO tempDAO;
	
	public TempVO selectTemp(TempVO vo) throws Exception {
		return tempDAO.selectTemp(vo);
	}
	
	public List<EgovMap> selectTempList(TempVO vo) throws Exception{
		return tempDAO.selectTempList(vo);
	}
	
	public int selectTempListCnt(TempVO vo) throws Exception{
		return tempDAO.selectTempListCnt(vo);
	}
	
	public String insertTemp(TempVO vo) throws Exception{
		tempDAO.insertTemp(vo);
		
		return null;
	}
	
	public void updateTemp(TempVO vo) throws Exception{
		tempDAO.updateTemp(vo);
	}
	
	public void deleteTemp(TempVO vo) throws Exception{
		tempDAO.deleteTemp(vo);
	}
}
