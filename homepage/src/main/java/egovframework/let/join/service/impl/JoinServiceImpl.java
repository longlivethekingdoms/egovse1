package egovframework.let.join.service.impl;

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
import egovframework.let.utl.sim.service.EgovFileScrty;

@Service("joinService")
public class JoinServiceImpl extends EgovAbstractServiceImpl implements JoinService{

	@Resource(name="joinDAO")
	private JoinDAO joinDAO;
	
	@Resource(name = "joinIdGnrService")
	private EgovIdGnrService idgenService;
	
	//ID 중복체크
	public int duplicateCheck(JoinVO vo) throws Exception{
		return joinDAO.duplicateCheck(vo);
	}
	
	//회원등록
	public String insertJoin(JoinVO vo) throws Exception {
		String esntlId = idgenService.getNextStringId();
		vo.setEsntlId(esntlId);
		
		//입력 비밀번호 암호화
		String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getEmplyrId());
		vo.setPassword(enpassword);
		
		//이메일 EgovStringUtil import는 egovframework.let.utl.fcc.service.EgovStringUtil
		if(!EgovStringUtil.isEmpty(vo.getEmailId()) && !EgovStringUtil.isEmpty(vo.getEmailDomain())) {
			vo.setEmailAdres(vo.getEmailId()+"@"+vo.getEmailDomain());
		}
		
		joinDAO.insertJoin(vo);
		return esntlId;
	}
}
