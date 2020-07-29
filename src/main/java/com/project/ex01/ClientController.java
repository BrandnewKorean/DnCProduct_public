package com.project.ex01;

<<<<<<< HEAD
import java.io.IOException;
=======
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Map;
>>>>>>> refs/remotes/origin/master

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

<<<<<<< HEAD
import com.github.scribejava.core.model.OAuth2AccessToken;
=======
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
>>>>>>> refs/remotes/origin/master

import service.ClientService;
import vo.ClientVO;

@Controller
public class ClientController {
	
	@Autowired
	ClientService service;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@RequestMapping(value = "googleLogin", produces = "application/x-www-form-urlencoded")
	public ModelAndView googleLogin(ModelAndView mv, String idtoken) throws GeneralSecurityException, IOException {
		System.out.println(idtoken);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(
                ((HttpURLConnection) (new URL("https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + idtoken.trim()))
                .openConnection()).getInputStream(), Charset.forName("UTF-8")));
		
		StringBuffer b = new StringBuffer();
		String inputLine;
		while((inputLine = in.readLine()) != null) {
			b.append(inputLine+"\n");
		}
		System.out.println(b.toString());
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,String> tokenPayload = objectMapper.readValue(b.toString(), objectMapper.getTypeFactory().constructMapType(Map.class, String.class, String.class));
		
		if(tokenPayload.get("email_verified") != null && tokenPayload.get("email") != null) {
			System.out.println(tokenPayload.get("name"));
			System.out.println(tokenPayload.get("email"));
		}

		mv.setViewName("jsonView");
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