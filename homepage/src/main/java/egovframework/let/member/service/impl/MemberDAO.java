package egovframework.let.member.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.let.board.service.BoardVO;
import egovframework.let.join.service.JoinVO;
import egovframework.let.member.service.MemberVO;
import egovframework.let.temp2.service.Temp2VO;

@Repository("memberDAO")
public class MemberDAO extends EgovAbstractMapper {
	
	//회원 id찾기
	public MemberVO findId(MemberVO vo) throws Exception {
		
		return selectOne("memberDAO.findId", vo);
	}

	//회원 비밀번호찾기
	public MemberVO findPassword(MemberVO vo) throws Exception {
		
		return selectOne("memberDAO.findPassword", vo);
	}

	public void passwordUpdate(MemberVO vo) throws Exception {
		update("memberDAO.passwordUpdate", vo);
	}
	
	//회원목록
	public List<EgovMap> selectMberList(MemberVO vo) throws Exception{
		return selectList("memberDAO.selectMberList", vo);
	}
	
	//회원목록 수
	public int selectMberListCnt(MemberVO vo) throws Exception{
		return selectOne("memberDAO.selectMberListCnt", vo);
	}
	
	//회원상세
	public EgovMap selectMber(MemberVO vo) throws Exception{
		return selectOne("memberDAO.selectMber", vo);
	}
	
}
