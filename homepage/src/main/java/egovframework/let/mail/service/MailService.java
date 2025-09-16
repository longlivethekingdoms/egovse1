package egovframework.let.mail.service;

import java.util.Properties;

import javax.mail.Session;

public interface MailService {
	
	//메일session값 셋팅(javax.mail.Session, Properties : java.util.Properties)
	Session mailSetting(Properties props) throws Exception;
	
	//메일보내기(javax.mail.Session)
	void sendMail(Session session, String title, String content, String receiver) throws Exception;
	
	
}