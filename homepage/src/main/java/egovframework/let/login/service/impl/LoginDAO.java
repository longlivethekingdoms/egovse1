package egovframework.let.login.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.egovframe.rte.psl.dataaccess.util.EgovMap;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.LoginVO;
import egovframework.let.board.service.BoardVO;
import egovframework.let.join.service.JoinVO;
import egovframework.let.temp2.service.Temp2VO;

@Repository("egovLoginDAO")
public class LoginDAO extends EgovAbstractMapper {
	
	public LoginVO actionLogin(LoginVO vo) throws Exception{
		return selectOne("egovLoginDAO.actionLogin", vo);
	}

}
