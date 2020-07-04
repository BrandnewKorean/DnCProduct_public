package com.project.ex01;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.ClientService;
import vo.ClientVO;

@Controller
public class ClientController {
	
	@Autowired
	ClientService service;
	
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
	
// ** Id Dup Check
	@RequestMapping(value = "/idDupCheck")
	public ModelAndView idDupCheck(ModelAndView mv, ClientVO cv) {
		// client 로 부터 전달된 id 의 존재 여부 확인 : selectOne()
		// => notNull (존재하면) : 사용불가
		// => Null (존재하지 않으면) : 사용가능 (먼저 입력한 ID 보관 )
		mv.addObject("ID", cv.getId());
		cv = service.selectOne(cv);
		if (cv != null) // 사용불가
			mv.addObject("idUse", "F");
		else
			mv.addObject("idUse", "T");
			mv.setViewName("cat/join/idDupCheck");
			return mv;
	}// idDupCheck
	
	
} // class