package com.project.ex01;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping(value = "diaryf")
	public ModelAndView diaryf(HttpServletRequest request, ModelAndView mv) {
		String id = (String)request.getSession().getAttribute("logID");
		if(id != null) {
			mv.setViewName("cat/diary/DiaryForm");
		}else {
			mv.setViewName("cat/Catmain");
		}
		return mv;
	}
	
	@RequestMapping(value = "logout")
	public ModelAndView logout(HttpServletRequest request, ModelAndView mv) {
		if(request.getSession().getAttribute("logID") != null) {
			request.getSession().invalidate();
			mv.addObject("result", true);
		}else {
			mv.addObject("result", false);
		}
		mv.setViewName("jsonView");
		return mv;
	}
	
	@RequestMapping(value = "clientInfo")
	public ModelAndView clientInfo(HttpServletRequest request, ModelAndView mv, String code) {
		ClientVO cv = new ClientVO();
		String id = (String)request.getSession().getAttribute("logID");
		
		if(id != null) {
			cv.setId(id);
			cv = service.selectOne(cv);
		}
		
		if(code.equals("json")) {
			mv.addObject("cv", cv);
			mv.setViewName("jsonView");
		}else {
			mv.addObject("client", cv);
			mv.setViewName("cat/login/Myinfo");
		}
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
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, ModelAndView mv, ClientVO cv) {
		String password = cv.getPassword();
		cv = service.selectOne(cv);
		
		if(cv != null) {
			if(password.equals(cv.getPassword())) {
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
	public ModelAndView join(ModelAndView mv, ClientVO cv) throws IOException{
		
		service.insert(cv);
		mv.setViewName("cat/Catmain");
		return mv;
	}
	
	@RequestMapping(value="idDuplicateCheck")
	public ModelAndView idDuplicateCheck(ModelAndView mv, ClientVO cv) {
		
		// client로 부터 전달된 id의 존재 여부 확인 : selectOne()
		// => NotNull(존재)이면 사용불가
		// => Null(미존재)이면 사용가능
		
		mv.addObject("ID", cv.getId());
		cv = service.selectOne(cv);
		
		if(cv != null) {
			mv.addObject("idUse","F");
		}else {
			mv.addObject("idUse","T");
		}
		mv.setViewName("join/idDuplicateCheck");
		return mv;
	} // idDuplicateCheck
	
} // class