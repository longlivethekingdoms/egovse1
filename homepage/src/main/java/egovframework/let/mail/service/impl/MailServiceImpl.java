package egovframework.let.mail.service.impl;

import egovframework.let.mail.service.MailService;

import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.egovframe.rte.fdl.property.EgovPropertyService;
import org.springframework.stereotype.Service;


@Service("mailService")
public class MailServiceImpl extends EgovAbstractServiceImpl implements MailService {
    
	
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertyService;
	
	final String encoding = "UTF-8";
	final String port = "465";
	final String smtpHost = "smtp.gmail.com";
    
	//메일session값 셋팅(javax.mail.Session)
	public Session mailSetting(Properties props) throws Exception{
		Session session = null;
		
		try {
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", smtpHost);//이메일 발송을 처리해줄 STMP 서버
			props.put("mail.smtp.port", port);//SMTP 통신 포트
			props.put("mail.smtp.auth", true);//SMTP 인증 기능
			props.put("mail.smtp.ssl.enable", true);//SSL 사용여부
			props.put("mail.smtp.ssl.trust", smtpHost);//SSL 신뢰여부
			props.put("mail.smtp.starttls.required", true);//TLS 보호 연결을 활성화하는 데 사용
			props.put("mail.smtp.ssl.protocols", "TLSv1.2");//SSL 버전
			props.put("mail.smtp.quit-wait", "false");//서버가 연결을 올바르게 종료했다는 응답을 확인
			props.put("mail.smtp.socketFactory.port", port);
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL 통신에 사용할 소켓통신 클래스
			props.put("mail.smtp.socketFactory.fallback", "false");
			
			session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(propertyService.getString("mail.gmailUser"), propertyService.getString("mail.gmailPassword"));
				}
			});
			
		} catch(Exception e) {
			System.out.println("실패");
		}
		
		return session;
	}
	
	//메일보내기
	public void sendMail(Session session, String title, String content, String receiver) throws Exception{
		//Message : javax.mail.Message
		Message msg = new MimeMessage(session);
		
		try {
			msg.setFrom(new InternetAddress(propertyService.getString("mail.gmailUser"), propertyService.getString("mail.gmailUserName"), encoding));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			msg.setSubject(title);
			msg.setContent(content, "text/html; charset=utf-8");
			
			Transport.send(msg);
			
		} catch(Exception e) {
			System.out.println("실패");
		}
	}
}
