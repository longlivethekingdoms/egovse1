package egovframework.let.test.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import egovframework.let.test.service.TestService;
import egovframework.let.test.service.TestVO;


@Service("testService")
public class TestServiceImpl extends EgovAbstractServiceImpl implements TestService{
	
	@Resource(name="testDAO")
	private TestDAO testDAO;
	
	@Resource(name = "egovTestIdGnrService")
	private EgovIdGnrService idgenService;
	
	//임시데이터 등록하기
	public String insertTest(TestVO vo) throws Exception {
		String id = idgenService.getNextStringId();
		vo.setTestId(id);
		testDAO.insertTest(vo);
		
		return id;
	}
	
	//임시데이터 목록 가져오기
	public List<EgovMap> selectTestList(TestVO vo) throws Exception{
		return testDAO.selectTestList(vo);
	}
	
	//임시데이터 목록 수
	public int selectTestListCnt(TestVO vo) throws Exception{
		return testDAO.selectTestListCnt(vo);
	}
	
	//임시데이터 상세
	public TestVO selectTest(TestVO vo) throws Exception{
		return testDAO.selectTest(vo);
	}
	
	//임시데이터 수정
	public void updateTest(TestVO vo) throws Exception{
		testDAO.updateTest(vo);
	}
	
	//임시데이터 삭제
	public void deleteTest(TestVO vo) throws Exception{
		testDAO.deleteTest(vo);
	}
}
