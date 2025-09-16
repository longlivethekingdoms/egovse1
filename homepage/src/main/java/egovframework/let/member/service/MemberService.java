package egovframework.let.member.service;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.util.EgovMap;

public interface MemberService {

	//회원ID찾기
	public MemberVO findId(MemberVO vo) throws Exception;
	
	//회원 비밀번호 찾기
	public MemberVO findPassword(MemberVO vo) throws Exception;

	//회원비밀번호업데이트
	public void passwordUpdate(MemberVO vo) throws Exception;
	
	//회원목록
	public List<EgovMap> selectMberList(MemberVO vo) throws Exception;
	
	//회원목록 수
	public int selectMberListCnt(MemberVO vo) throws Exception;
	
	//회원상세
	EgovMap selectMber(MemberVO vo) throws Exception;
	
}
