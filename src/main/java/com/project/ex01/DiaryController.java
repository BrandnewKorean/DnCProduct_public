package com.project.ex01;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.DiaryService;
import vo.DiaryVO;

@Controller
public class DiaryController {
	
	@Autowired
	DiaryService service;
	
	@RequestMapping(value = "diaryf")
	public ModelAndView diaryf(ModelAndView mv) {
		mv.setViewName("cat/diary/DiaryForm");
		return mv;
	}
	
	@RequestMapping(value = "diary")
	public ModelAndView diary(HttpServletRequest request, ModelAndView mv, DiaryVO dv) {
		System.out.println(dv);
		String id = (String)request.getSession().getAttribute("logID");
		
		if(id != null) {
			dv.setId(id);
			dv = service.selectOne(dv);
			if(dv != null) {
				mv.addObject("code", 0);
			}else {
				mv.addObject("code", 1);
			}
		}else {
			mv.addObject("code", 2);
		}
		
		mv.addObject("dv", dv);
		mv.setViewName("jsonView");
		return mv;
	}
}
