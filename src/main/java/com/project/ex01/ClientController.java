package com.project.ex01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.core.model.OAuth2AccessToken;

import kakao.KakaoLoginBO;
import naver.NaverLoginBO;
import service.ClientService;
import vo.ClientVO;

@Controller
public class ClientController {
		
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;
		
	@Autowired
	ClientService service;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	GoogleConnectionFactory googleConnectionFactory;
	
	@Autowired
	OAuth2Parameters googleOAuth2Parameters;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	@RequestMapping(value = "loginf")
	public ModelAndView loginf(ModelAndView mv, HttpSession session) {
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String googleurl = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		String naverurl = naverLoginBO.getAuthorizationUrl(session);
		String kakaourl = KakaoLoginBO.getAuthorizationUrl(session);
		
		mv.addObject("google_url", googleurl);
		mv.addObject("naver_url", naverurl);
		mv.addObject("kakao_url", kakaourl);
		mv.setViewName("login/LoginForm");
		return mv;
	}
	
	@RequestMapping(value = "loginsuccess")
	public ModelAndView loginsuccess(ModelAndView mv) {
		mv.setViewName("login/LoginSuccess");
		return mv;
	}
	
	@RequestMapping(value = "kakaologin", method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
	public ModelAndView kakaologin(ModelAndView mv, @RequestParam String code, HttpSession session) throws ClientProtocolException, IOException {
		String email = null;
		String name = null;
		String id = null;
		
		JsonNode node = KakaoLoginBO.getAccessToken(code);
		JsonNode accessToken = node.get("access_token");
		JsonNode userInfo = KakaoLoginBO.getkakaoUserInfo(accessToken);
		
		System.out.println("userInfo => "+userInfo);
		
		JsonNode properties = userInfo.path("properties");
		JsonNode kakao_account = userInfo.path("kakao_account");
		
		
		id = "kakao"+userInfo.path("id").asText();
		email = kakao_account.path("email").asText();
		name = properties.path("nickname").asText();
		
		ClientVO cv = new ClientVO();
		cv.setId(id);
		cv = service.selectOne(cv);

		if(cv != null) {
			session.setAttribute("logID", id);
			mv.setViewName("redirect: loginsuccess");
		}else {
			mv.addObject("social_type", "kakao");
			mv.addObject("social_name", name);
			mv.addObject("social_email", email);
			mv.addObject("social_id", id);
			mv.setViewName("join/JoinForm");
		}
		
		return mv;
	}
	
	@RequestMapping(value = "naverlogin", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView naverlogin(ModelAndView mv, @RequestParam String code, @RequestParam String state, HttpSession session) throws IOException, ParseException {
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
		apiResult = naverLoginBO.getUserProfile(oauthToken);
		
		System.out.println(apiResult);
		
		String id = null;
		String name = null;
		String email = null;
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		
		JSONObject response_obj = (JSONObject)jsonObj.get("response");
		System.out.println(response_obj);
		
		if(response_obj.get("id") != null && response_obj.get("name") != null && response_obj.get("email") != null) {
			id = "naver"+(String)response_obj.get("id");
			name = (String)response_obj.get("name");
			email = (String)response_obj.get("email");
			
			ClientVO cv = new ClientVO();
			cv.setId(id);
			cv = service.selectOne(cv);

			if(cv != null) {
				session.setAttribute("logID", id);
				mv.setViewName("redirect: loginsuccess");
			}else {
				mv.addObject("social_type", "naver");
				mv.addObject("social_name", name);
				mv.addObject("social_email", email);
				mv.addObject("social_id", id);
				mv.setViewName("join/JoinForm");
			}
		}else {
			String reprompt = naverLoginBO.getAuthorizationUrl(session)+"&auth_type=reprompt";
			
			mv.setViewName("redirect: "+reprompt);
		}
		
		return mv;
	}
	
	@RequestMapping(value = "googlelogin", method = RequestMethod.GET)
	public ModelAndView googlelogin(HttpServletRequest request, ModelAndView mv, @RequestParam String code) throws MalformedURLException, IOException {
		System.out.println("googlecallback");
		
		String name = null;
		String email = null;
		String sub = null;
		
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, googleOAuth2Parameters.getRedirectUri(), null);
		
		String accessToken = accessGrant.getAccessToken();
		Long expireTime = accessGrant.getExpireTime();
		
		if(expireTime != null && expireTime < System.currentTimeMillis()) {
			accessToken = accessGrant.getRefreshToken();
		}
				
		BufferedReader in = new BufferedReader(new InputStreamReader(
                ((HttpURLConnection) (new URL("https://www.googleapis.com/oauth2/v3/userinfo?access_token=" + accessToken.trim()))
                .openConnection()).getInputStream(), Charset.forName("UTF-8")));
		
		StringBuffer b = new StringBuffer();
		String inputLine;
		while((inputLine = in.readLine()) != null) {
			b.append(inputLine+"\n");
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,String> tokenPayload = objectMapper.readValue(b.toString(), objectMapper.getTypeFactory().constructMapType(Map.class, String.class, String.class));
		
		if(tokenPayload.get("name") != null && tokenPayload.get("email") != null && tokenPayload.get("sub") != null) {
			name = tokenPayload.get("name");
			email = tokenPayload.get("email");
			sub = "google"+tokenPayload.get("sub");
		}
		
		ClientVO cv = new ClientVO();
		cv.setId(sub);
		cv = service.selectOne(cv);

		if(cv != null) {
			request.getSession().setAttribute("logID", sub);
			mv.setViewName("redirect: loginsuccess");
		}else {
			mv.addObject("social_type", "google");
			mv.addObject("social_name", name);
			mv.addObject("social_email", email);
			mv.addObject("social_id", sub);
			mv.setViewName("join/JoinForm");
		}
		
		return mv;
	}
			
	@RequestMapping(value = "termsuse")
	public ModelAndView termsuse(ModelAndView mv) {
		mv.setViewName("join/TermsofUse");
		return mv;
	} // cat
	

	
	@RequestMapping(value = "termsprivacy")
	public ModelAndView termsprivacy(ModelAndView mv) {
		mv.setViewName("join/TermsofPrivacy");
		return mv;
	} // cat

	
	@RequestMapping(value = "termslocation")
	public ModelAndView termslocation(ModelAndView mv) {
		mv.setViewName("join/TermsofLocationInformation");
		return mv;
	} // cat
	
	
	
	
	@RequestMapping(value = "logout")
	public ModelAndView logout(HttpServletRequest request, ModelAndView mv) {
		String id = (String)request.getSession().getAttribute("logID");
		if(id != null) {
			request.getSession().invalidate();
			mv.addObject("result", true);
		}else {
			mv.addObject("result", false);
		}
		mv.setViewName("jsonView");
		return mv;
	}
	
	
	@RequestMapping(value = "clientInfo")
	public ModelAndView clientInfo(HttpServletRequest request, ModelAndView mv, String code, ClientVO cv) {
		String id = (String)request.getSession().getAttribute("logID");
		cv.setId(id);
		cv = service.selectOne(cv);
		mv.addObject("cv", cv);
		if("json".equals(code)) {
			mv.setViewName("jsonView");
		}else {
			mv.setViewName("login/Myinfo");
		}
		return mv;
	}
	
	@RequestMapping(value="delete")
	public ModelAndView delete(ModelAndView mv, HttpServletRequest request, ClientVO cv) {
		String id = "";
		HttpSession session = request.getSession(false);
		id = (String)session.getAttribute("logID");
		cv.setId(id);
		System.out.println(cv);
		if(service.delete(cv) > 0) {
			session.invalidate();
			mv.addObject("code",0);
		}else {
			mv.addObject("code",1);
		}
		mv.setViewName("jsonView");
		return mv;
	} // delete
	
	@RequestMapping(value = "updatef")
	public ModelAndView updatef(HttpServletRequest request, ModelAndView mv) {
		ClientVO cv = new ClientVO();
		String id = (String)request.getSession().getAttribute("logID");
		cv.setId(id);
		cv = service.selectOne(cv);
		mv.addObject("cv", cv);
		mv.setViewName("login/MyinfoUpdate");
		return mv;
	}

	@RequestMapping(value = "update")
	public ModelAndView update(HttpServletRequest request, ModelAndView mv, ClientVO cv) {
		if(service.update(cv) > 0) {
			mv.addObject("code", 0);
		}else {
			mv.addObject("code", 1);
		}
		mv.setViewName("jsonView");
		return mv;
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, ModelAndView mv, ClientVO cv) {
		String password = cv.getPassword();
		cv = service.selectOne(cv);
		if(cv != null) {
			if(passwordEncoder.matches(password, cv.getPassword())) {
				request.getSession().setAttribute("logID", cv.getId());
				mv.addObject("code", 0);
			}else {
				mv.addObject("code", 1);
			}
		}else {
			mv.addObject("code", 2);
		}
		
		mv.setViewName("jsonView");
		return mv;
	}
	
	@RequestMapping(value = {"/","/home"})
	public ModelAndView home(ModelAndView mv) {
		mv.setViewName("Homepage");
		return mv;
	}
	
	@RequestMapping(value = "dogloginf")
	public ModelAndView dogloginf(ModelAndView mv) {
		mv.setViewName("login/LoginForm");
		return mv;
	}
	
	@RequestMapping(value = "dogmain")
	public ModelAndView dogmain(HttpServletRequest request, ModelAndView mv) {
		mv.setViewName("dog/Dogmain");
		return mv;
	}
	@RequestMapping(value = "catmain")
	public ModelAndView catmain(HttpServletRequest request, ModelAndView mv) {
		mv.setViewName("cat/Catmain");
		return mv;
	}
	
	@RequestMapping(value="JoinTerms")
	public ModelAndView JoinTerms(ModelAndView mv) {
		mv.setViewName("join/JoinTerms");
		return mv;
	} // cat
	

	@RequestMapping(value="JoinForm")
	public ModelAndView JoinForm(ModelAndView mv) {
		mv.setViewName("join/JoinForm");
		return mv;
	}
	
	@RequestMapping(value="join")
	public ModelAndView join(ModelAndView mv, ClientVO cv) {
		cv.setPassword(passwordEncoder.encode(cv.getPassword()));
		if (service.insert(cv) > 0) {
			mv.addObject("result",true);
		}else {
			mv.addObject("result",false);
		}
		mv.setViewName("jsonView");
		return mv;
	}
	
	@RequestMapping(value="selectOne",method=RequestMethod.POST)
	public ModelAndView selectOne(ModelAndView mv, ClientVO cv) {
		cv = service.selectOne(cv);
		if(cv!=null) {
			mv.addObject("result",false);
		}else {
			mv.addObject("result",true);
		}
		mv.setViewName("jsonView");
		return mv;
	}
	
	@RequestMapping(value = "juso")
	public ModelAndView juso(ModelAndView mv) {
		mv.setViewName("popup/jusoPopup");
		return mv;
	}
	@RequestMapping(value = "FindId")
	public ModelAndView findId(ModelAndView mv,ClientVO cv) {
		cv = service.sendFindId(cv);
		System.out.println(cv);
		if(cv != null) {
			mv.addObject("email",cv.getEmail());
			mv.addObject("result",true);
			
			String setfrom = "DnCProductSystem@gmail.com";
			String tomail = cv.getEmail();
			String title = "문의하신 계정 정보입니다.";
			String content = 
					"<h1>DnCProduct</h1>"
					+ "<h3>요청하신 계정은</h3>"
					+ "<p style=\"font-weight:bold; font-size:15px;\">"+ cv.getId()+ "</p>"
					+ "<hr>"
					+ "입니다.";
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8");
				
				messageHelper.setFrom(setfrom);
				messageHelper.setTo(tomail);
				messageHelper.setSubject(title);
				messageHelper.setText(content,true);
				
				mailSender.send(message);
				
			} catch (Exception e) {
				System.out.println("mailSending Exception => "+e.toString());
			}

		}else {
			mv.addObject("result", false);
		}
		mv.setViewName("jsonView");
		return mv;
	}
	@RequestMapping(value = "FindIdForm")
	public ModelAndView findIdForm(ModelAndView mv) {
		mv.setViewName("login/FindIdForm");
		return mv;
	}
	
//	@RequestMapping(value = "/find")
} // class