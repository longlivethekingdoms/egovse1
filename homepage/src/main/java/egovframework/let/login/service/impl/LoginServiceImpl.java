package egovframework.let.login.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.Resources;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.idgnr.EgovIdGnrService;
import org.egovframe.rte.fdl.string.EgovStringUtil;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.LoginVO;
import egovframework.let.board.service.BoardService;
import egovframework.let.board.service.BoardVO;
import egovframework.let.join.service.JoinService;
import egovframework.let.join.service.JoinVO;
import egovframework.let.login.service.LoginService;
import egovframework.let.utl.sim.service.EgovFileScrty;

@Service("egovLoginService")
public class LoginServiceImpl extends EgovAbstractServiceImpl implements LoginService{

	@Resource(name = "egovLoginDAO")
	private LoginDAO loginDAO;
	
	//일반 로그인을 처리한다
	public LoginVO actionLogin(LoginVO vo) throws Exception{
		//입력한 비밀번호를 암호화한다
		String enpassword = EgovFileScrty.encryptPassword(vo.getPassword(), vo.getId());
		vo.setPassword(enpassword);
		
		//아이디와 암호화된 비밀번호가 DB와 일치하는지 확인한다.
		LoginVO loginVO = loginDAO.actionLogin(vo);
		
		if(loginVO != null && !loginVO.getId().equals("")&&!loginVO.getPassword().equals("")) {
			return loginVO;
		}else {
			loginVO = new LoginVO();
		}
		
		return loginVO;
	}
	
}
