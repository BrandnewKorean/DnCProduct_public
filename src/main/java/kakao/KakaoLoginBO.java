package kakao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KakaoLoginBO {
	private final static String CLIENT_ID = "d85e6d8b9d6f56ce308d1541360aea26";
	private final static String REDIRECT_URI = "http://localhost:8080/ex01/kakaologin";
	
	public static String getAuthorizationUrl(HttpSession session) {
		String kakaoUrl = "https://kauth.kakao.com/oauth/authorize?"+"client_id=" +CLIENT_ID+"&redirect_uri=" +REDIRECT_URI+"&response_type=code";
		return kakaoUrl;
	}
	
	public static JsonNode getAccessToken(String autorize_code) throws ClientProtocolException, IOException {
		final String RequestUrl = "https://kauth.kakao.com/oauth/token";
		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", CLIENT_ID));
		postParams.add(new BasicNameValuePair("redirect_uri",REDIRECT_URI));
		postParams.add(new BasicNameValuePair("code", autorize_code));
		
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		JsonNode returnNode = null;
		
		post.setEntity(new UrlEncodedFormEntity(postParams));
		final HttpResponse response = client.execute(post);
		
		ObjectMapper mapper = new ObjectMapper();
		returnNode = mapper.readTree(response.getEntity().getContent());
		
		return returnNode;
	}
	
	public static JsonNode getkakaoUserInfo(JsonNode accessToken) throws ClientProtocolException, IOException {
		final String RequestUrl = "https://kapi.kakao.com/v2/user/me";
		final HttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestUrl);
		post.addHeader("Authorization","Bearer "+accessToken);
		JsonNode returnNode = null;
		
		final HttpResponse response = client.execute(post);
		ObjectMapper mapper = new ObjectMapper();
		returnNode = mapper.readTree(response.getEntity().getContent());
		
		return returnNode;
	}
}
