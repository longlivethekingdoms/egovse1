package egovframework.let.api.naver.service;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class NaverLoginApi extends DefaultApi20 {
	protected NaverLoginApi(){}

	private static class InstanceHolder{
		private static final NaverLoginApi INSTANCE = new NaverLoginApi();
	}

	public static NaverLoginApi instance(){
		return InstanceHolder.INSTANCE;
	}
	
	//접근 토큰 발급/갱신/삭제 요청
	@Override
	public String getAccessTokenEndpoint() {
		return "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
	}

	//네이버 로그인 인증 요청
	@Override
	protected String getAuthorizationBaseUrl() {
		return"https://nid.naver.com/oauth2.0/authorize";
	}

}
