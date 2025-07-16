package egovframework.let.temp2.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import egovframework.let.temp2.service.Temp2Service;
import egovframework.let.temp2.service.Temp2VO;

@Service("temp2Service")
public class Temp2ServiceImpl extends EgovAbstractServiceImpl implements Temp2Service{
	
	@Resource(name="temp2DAO")
	private Temp2DAO temp2DAO;
	
	@Resource(name = "egovTempIdGnrService")
	private EgovIdGnrService idgenService;
	
	//임시데이터 등록하기
	public String insertTemp(Temp2VO vo) throws Exception {
		String id = idgenService.getNextStringId();
		vo.setTempId(id);
		temp2DAO.insertTemp(vo);
		
		return id;
	}
	
	//임시데이터 목록 가져오기
	public List<EgovMap> selectTempList(Temp2VO vo) throws Exception{
		return temp2DAO.selectTempList(vo);
	}
	
	//임시데이터 목록 수
	public int selectTempListCnt(Temp2VO vo) throws Exception{
		return temp2DAO.selectTempListCnt(vo);
	}
	
	//임시데이터 상세
	public Temp2VO selectTemp(Temp2VO vo) throws Exception{
		return temp2DAO.selectTemp(vo);
	}
	
	//임시데이터 수정
	public void updateTemp(Temp2VO vo) throws Exception{
		temp2DAO.updateTemp(vo);
	}
	
	//임시데이터 삭제
	public void deleteTemp(Temp2VO vo) throws Exception{
		temp2DAO.deleteTemp(vo);
	}
	
	
}
