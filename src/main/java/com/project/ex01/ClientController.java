package com.project.ex01;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.JsonNode;

import service.ClientService;
import vo.ClientVO;

@Controller
public class ClientController {
	
	@Autowired
	ClientService service;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
//	@RequestMapping(value = "kakaologin", produces = "application/json", method = { RequestMethod.GET, RequestMethod.POST })
//	public ModelAndView kakaoLogin(@RequestParam("code") String code, 
//			HttpServletRequest request, HttpServletResponse response, 
//			HttpSession session, ModelAndView mv) throws Exception {
//		
//		JsonNode node = KakaoController.getAccessToken(code);
//		// accessToken에 사용자의 로그인한 모든 정보가 들어있음 
//		JsonNode accessToken = node.get("access_token"); // 사용자의 정보 
//		JsonNode userInfo = KakaoController.getKakaoUserInfo(accessToken); 
//		String kemail = null; 
//		String kname = null; 
//		String kgender = null; 
//		String kbirthday = null; 
//		String kage = null; 
//		String kimage = null;
//		
//		// 유저정보 카카오에서 가져오기 Get properties 
//		JsonNode properties = userInfo.path("properties"); 
//		JsonNode kakao_account = userInfo.path("kakao_account"); 
//		kemail = kakao_account.path("email").asText(); 
//		kname = properties.path("nickname").asText(); 
//		kimage = properties.path("profile_image").asText(); 
//		kgender = kakao_account.path("gender").asText();
//		kbirthday = kakao_account.path("birthday").asText(); kage = kakao_account.path("age_range").asText(); 
//		
//		session.setAttribute("kemail", kemail); 
//		session.setAttribute("kname", kname); 
//		session.setAttribute("kimage", kimage); 
//		session.setAttribute("kgender", kgender); 
//		session.setAttribute("kbirthday", kbirthday); 
//		session.setAttribute("kage", kage); 
//		
//		mv.setViewName("main");
//		
//	return mv;
//	
//	}//login	
//	
//	@RequestMapping(value = "LoginForm", method = RequestMethod.GET)
//	public ModelAndView memberLoginForm(HttpSession session,ModelAndView mv) {
//		
//		/* 네아로 인증 URL을 생성하기 위하여 getAuthorizationUrl을 호출 */
//		//String kakaoUrl = KakaoController.getAuthorizationUrl(session);
//
//		/* 생성한 인증 URL을 View로 전달 */
//		//mv.setViewName("cat/login/LoginForm");
//
//		// 카카오 로그인
//		//mv.addObject("kakao_url", kakaoUrl);
//
//	//	return mv;
////	}// end memberLoginForm()
	
	@RequestMapping(value = "googletest")
	public ModelAndView test(ModelAndView mv) {
		mv.setViewName("cat/login/google");
		return mv;
	}
	

	@RequestMapping(value = "termsuse")
	public ModelAndView termsuse(ModelAndView mv) {
		mv.setViewName("cat/join/TermsofUse");
		return mv;
	}
	
	@RequestMapping(value = "termsprivacy")
	public ModelAndView termsprivacy(ModelAndView mv) {
		mv.setViewName("cat/join/TermsofPrivacy");
		return mv;
	}
	
	@RequestMapping(value = "termslocation")
	public ModelAndView termslocation(ModelAndView mv) {
		mv.setViewName("cat/join/TermsofLocationInformation");
		return mv;
	}
	
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
		
		if(code.equals("json")) {
			mv.setViewName("jsonView");
		}else {
			mv.setViewName("cat/login/Myinfo");
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
		mv.setViewName("cat/login/MyinfoUpdate");
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
		mv.setViewName("dog/login/LoginForm");
		return mv;
	}
	
	@RequestMapping(value = "catloginf")
	public ModelAndView catloginf(ModelAndView mv) {
		mv.setViewName("cat/login/LoginForm");
		return mv;
	}
	
	@RequestMapping(value = "dogmain")
	public ModelAndView dogmain(ModelAndView mv) {
		mv.setViewName("dog/Dogmain");
		return mv;
	}
	
	@RequestMapping(value = "catmain")
	public ModelAndView catmain(ModelAndView mv) {
		mv.setViewName("cat/Catmain");
		return mv;
	}
	
	@RequestMapping(value="JoinTerms")
	public ModelAndView JoinTerms(ModelAndView mv) {
		mv.setViewName("cat/join/JoinTerms");
		return mv;
	}
	
	@RequestMapping(value="JoinForm")
	public ModelAndView JoinForm(ModelAndView mv) {
		mv.setViewName("cat/join/JoinForm");
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
} // class