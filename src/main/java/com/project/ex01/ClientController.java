package com.project.ex01;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.ClientService;
import vo.ClientVO;

@Controller
public class ClientController {
	
	@Autowired
	ClientService service;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder ;
	
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
	
	
	@RequestMapping(value="logout")
	public ModelAndView logout(ModelAndView mv, HttpServletRequest request) {
		String id = (String)request.getSession().getAttribute("logID");
		if(id!=null) {
			request.getSession().invalidate();
			mv.addObject("result",true);
		}else{
			mv.addObject("result",false);
		}
		mv.setViewName("jsonView");
		return mv;
	} // logout
	
	
	@RequestMapping(value="clientInfo")
	public ModelAndView clientInfo(HttpServletRequest request,ModelAndView mv, String code, ClientVO cv) {
		String id = (String)request.getSession().getAttribute("logID");
		
		cv.setId(id);
		cv = service.selectOne(cv);
		mv.addObject("cv",cv);
		
		if(code.equals("json")) {
			mv.setViewName("jsonView");
		}else {
			mv.setViewName("cat/login/Myinfo");
		}
		return mv;
	}// clientInfo
	
	
	

	// code 0 : 로그인 되어 있다.
	// code 1 : 비밀번호 일치 하지 않는다.
	// code 2 : 아이디 일치 하지 않는다.

	@RequestMapping(value="delete")
	public ModelAndView delete(ModelAndView mv, HttpServletRequest request, ClientVO cv) {
		
//		// 1. 로그인 여부 확인
//		String id = "";
//		HttpSession session = request.getSession(false);
//		if(session != null && session.getAttribute("logID")!=null) {
//			id = (String)session.getAttribute("logID");
//		}else {
//			mv.setViewName("cat/login/LoginForm");
//			return mv;
//		}
//		
//		
		// 2. Login확인 -> 회원 탈퇴
		String id = "";
		HttpSession session = request.getSession(false);
		id = (String)session.getAttribute("logID");
		cv.setId(id);
//		mv.addObject("deleteID",id);
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
	
	
	
	
	
	@RequestMapping(value="update")
	public ModelAndView update(HttpServletRequest request, ClientVO cv, ModelAndView mv) {
		if(service.update(cv) > 0) {
			mv.addObject("code",0);
		}else {
			mv.addObject("code",1);
		}
		mv.setViewName("jsonView");
		return mv;
	} // update
	
	@RequestMapping(value="updatef")
	public ModelAndView updatef(HttpServletRequest request, ModelAndView mv, ClientVO cv) {
		
		String id = (String)request.getSession().getAttribute("logID");
		
		cv.setId(id);
		cv = service.selectOne(cv);
		
		mv.addObject("cv",cv);
		mv.setViewName("cat/login/MyinfoUpdate");
		return mv;
		
	} // updateForm
	
	
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, ModelAndView mv, ClientVO cv) {
		String password = cv.getPassword();
		cv = service.selectOne(cv); // 특정 id에 있는 모든 값을 cv에 넣자
		
		if(cv != null) {
			//if(password.contentEquals(cv.getPassword())) {
			if(passwordEncoder.matches(password, cv.getPassword())) {
				request.getSession().setAttribute("logID", cv.getId());
				mv.addObject("code", 0);
			}else {
				mv.addObject("code",1);
			}
	}else {
		mv.addObject("code",2);
	}
		mv.setViewName("jsonView");
		return mv;
	} // login
	
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