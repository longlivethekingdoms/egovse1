package egovframework.let.test.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.let.test.service.TestVO;

@Repository("testDAO")
public class TestDAO extends EgovAbstractMapper {
	//임시데이터 등록
		public void insertTest(TestVO vo) throws Exception{
			insert("testDAO.insertTest", vo);
		}
		
		//임시데이터 가져오기
		public TestVO selectTest(TestVO vo) throws Exception{
			return selectOne("testDAO.selectTest", vo);
		}
		
		//임시데이터 목록 가져오기
		public List<EgovMap> selectTestList(TestVO vo) throws Exception{
			return selectList("testDAO.selectTestList", vo);
		}
		
		//임시데이터 목록 수
		public int selectTestListCnt(TestVO vo) throws Exception{
			return selectOne("testDAO.selectTestListCnt", vo);
		}
		
		//임시데이터 수정
		public void updateTest(TestVO vo) throws Exception{
			update("testDAO.updateTest", vo);
		}
		
		//임시데이터 삭제
		public void deleteTest(TestVO vo) throws Exception{
			delete("testDAO.deleteTest", vo);
		}	
}
