package egovframework.let.test.service;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

public interface TestService {
	
	//임시데이터 가져오기
		public TestVO selectTest(TestVO vo) throws Exception;
		
		//임시데이터 등록하기
		public String insertTest(TestVO vo) throws Exception;
		
		//임시데이터 목록 가져오기
		public List<EgovMap> selectTestList(TestVO vo) throws Exception;
		
		//임시데이터 목록 수
		public int selectTestListCnt(TestVO vo) throws Exception;
		
		//임시데이터 수정하기
		public void updateTest(TestVO vo) throws Exception;
		
		//임시데이터 삭제하기
		public void deleteTest(TestVO vo) throws Exception;

}
