package egovframework.let.join.service;

import java.io.Serializable;

import egovframework.com.cmm.ComDefaultVO;

public class JoinVO extends ComDefaultVO implements Serializable {
	
	//아이디
	private String emplyrId;
	//이름
	private String userNm;

	//비번
	private String password;
	//비번힌트
	private String passwordHint;
	//비번정답
	private String passwordCnsr;
	//사용자상태코드
	private String emplySttusCode;
	//회원고유ID
	private String esntlId;
	//이메일
	private String emailAdres;
	//이메일Id
	private String emailId;
	//이메일도메인
	private String emailDomain;
	//로그인타입
	private String loginType;
	//가입일자
	private java.util.Date sbscrbDe;
	//약관동의 - 이용약관
	private String agree01;
	//약관동의 - 개인정보 수집
	private String agree02;
	//약관동의 - 개인정보 제3자제공
	private String agree03;
	
	public String getEmplyrId() {
		return emplyrId;
	}
	public void setEmplyrId(String emplyrId) {
		this.emplyrId = emplyrId;
	}
	
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordHint() {
		return passwordHint;
	}
	public void setPasswordHint(String passwordHint) {
		this.passwordHint = passwordHint;
	}
	public String getPasswordCnsr() {
		return passwordCnsr;
	}
	public void setPasswordCnsr(String passwordCnsr) {
		this.passwordCnsr = passwordCnsr;
	}
	public String getEmplySttusCode() {
		return emplySttusCode;
	}
	public void setEmplySttusCode(String emplySttusCode) {
		this.emplySttusCode = emplySttusCode;
	}
	public String getEsntlId() {
		return esntlId;
	}
	public void setEsntlId(String esntlId) {
		this.esntlId = esntlId;
	}
	public String getEmailAdres() {
		return emailAdres;
	}
	public void setEmailAdres(String emailAdres) {
		this.emailAdres = emailAdres;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getEmailDomain() {
		return emailDomain;
	}
	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}
	public String getLoginType() {
		return loginType;
	}
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	public java.util.Date getSbscrbDe() {
		return sbscrbDe;
	}
	public void setSbscrbDe(java.util.Date sbscrbDe) {
		this.sbscrbDe = sbscrbDe;
	}
	public String getAgree01() {
		return agree01;
	}
	public void setAgree01(String agree01) {
		this.agree01 = agree01;
	}
	public String getAgree02() {
		return agree02;
	}
	public void setAgree02(String agree02) {
		this.agree02 = agree02;
	}
	public String getAgree03() {
		return agree03;
	}
	public void setAgree03(String agree03) {
		this.agree03 = agree03;
	}
	
	
	
}
