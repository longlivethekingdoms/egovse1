package egovframework.let.api.naver.service.impl;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.util.StringUtils;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth20Service;

import egovframework.com.cmm.service.Globals;
import egovframework.let.api.naver.service.NaverLoginApi;
import egovframework.let.api.naver.service.NaverLoginService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NaverLoginServiceImpl extends EgovAbstractServiceImpl implements NaverLoginService {

	private final static String CLIENT_ID = Globals.NAVER_CLIENTID;
	private final static String CLIENT_SECRET = Globals.NAVER_CLIENTSECRET;
	private final static String REDIRECT_URI = Globals.NAVER_REDIRECTURI;
	private final static String SESSION_STATE = "oauth_state";
	/*프로필 조회 API URL*/
	private final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
	
	@Override
	public String getAuthorizationUri(HttpSession session, String domain, String port) {
		String redirectUri = "http://" + domain + ":" + port + REDIRECT_URI;
		
		String state = generateRandomString();
		
		setSession(session, state);
		
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(CLIENT_ID)
				.apiSecret(CLIENT_SECRET)
				.callback(redirectUri)
				.state(state)
				.build(NaverLoginApi.instance());
		
		return oauthService.getAuthorizationUrl();
	}

	@Override
	public OAuth2AccessToken getAccessToken(HttpSession session, String code, String state, String domain, String port) throws IOException {
		String redirectUri = "http://" + domain + ":" + port + REDIRECT_URI;
		
		String sessionState = getSession(session);
		if(StringUtils.pathEquals(sessionState, state)) {
			
			OAuth20Service oauthService = new ServiceBuilder()
					.apiKey(CLIENT_ID)
					.apiSecret(CLIENT_SECRET)
					.callback(redirectUri)
					.build(NaverLoginApi.instance());
			
			OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
			return accessToken;
		}
		return null;
	}

	@Override
	public String generateRandomString() {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString();
	}

	@Override
	public void setSession(HttpSession session, String state) {
		// TODO Auto-generated method stub
		session.setAttribute(SESSION_STATE, state);
	}

	@Override
	public String getSession(HttpSession session) {
		// TODO Auto-generated method stub
		return (String) session.getAttribute(SESSION_STATE);
	}
	
	@Override
	public String getUserProfile(OAuth2AccessToken oauthToken) throws IOException {
		
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		Request request = new Request.Builder()
				.url(PROFILE_API_URL)
				.method("GET", null)
				.addHeader("Content-Type", "application/x-www-form-urlencoded")
				.addHeader("Authorization", "Bearer" + oauthToken.getAccessToken())
				.build();
		Response response = client.newCall(request).execute();
		
		return response.body().string();
	}

}
