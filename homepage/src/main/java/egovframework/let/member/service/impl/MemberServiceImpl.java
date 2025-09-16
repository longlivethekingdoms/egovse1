package egovframework.let.member.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.string.EgovStringUtil;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import egovframework.let.board.service.BoardService;
import egovframework.let.board.service.BoardVO;
import egovframework.let.join.service.JoinService;
import egovframework.let.join.service.JoinVO;
import egovframework.let.member.service.MemberService;
import egovframework.let.member.service.MemberVO;
import egovframework.let.utl.sim.service.EgovFileScrty;

@Service("memberService")
public class MemberServiceImpl extends EgovAbstractServiceImpl implements MemberService{

	@Resource(name="memberDAO")
	private MemberDAO memberDAO;
	
	@Override
	public MemberVO findId(MemberVO vo) throws Exception {
		return memberDAO.findId(vo);
	}

	@Override
	public MemberVO findPassword(MemberVO vo) throws Exception {
		return memberDAO.findPassword(vo);
	}

	@Override
	public void passwordUpdate(MemberVO vo) throws Exception {
		//입력한 비밀번호를 암호화한다.
		String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getEmplyrId());
		vo.setPassword(enpassword);
		
		memberDAO.passwordUpdate(vo);
	}
	
	//회원목록
	public List<EgovMap> selectMberList(MemberVO vo) throws Exception{
		return memberDAO.selectMberList(vo);
	}
	
	//회원목록 수
	public int selectMberListCnt(MemberVO vo) throws Exception{
		return memberDAO.selectMberListCnt(vo);
	}

	
	//회원상세
	public EgovMap selectMber(MemberVO vo) throws Exception{
		return memberDAO.selectMber(vo);
	}
}
