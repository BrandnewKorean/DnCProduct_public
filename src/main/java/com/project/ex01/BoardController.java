package com.project.ex01;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.CatBoardService;
import vo.CatBoardVO;

@Controller
public class BoardController {
	@Autowired
	CatBoardService service;
	
	@RequestMapping(value="catboard")
	public ModelAndView catboard(ModelAndView mv) {
		List<CatBoardVO> list = service.selectList();
		
		mv.addObject("dnc",list);
		mv.setViewName("cat/board/catboard");
		
		return mv;
	} // boardlist
	
	@RequestMapping(value = "catboardinsertf")
	public ModelAndView catboardinsertf(ModelAndView mv) {
		mv.setViewName("cat/board/catboardinsertf");
		return mv;
	}
	
	@RequestMapping(value="catboardinsert")
	public ModelAndView catboardinsert(HttpServletRequest request, ModelAndView mv, CatBoardVO bv) {
		String id = (String)request.getSession().getAttribute("logID");

		if(id != null) {
			bv.setId(id);
			if(service.insert(bv) > 0) {
				// 글 등록 성공 -> list 출력
				mv.addObject("bcode", 0);
			}else {
				// 글 등록 실패 -> 다시 시도하기
				mv.addObject("bcode", 1);
			}
		}else {
			mv.addObject("bcode", 2);
		}
		
		mv.setViewName("jsonView");
		return mv;
		
	} // catboardinsert
	
	
	
	@RequestMapping(value="catboardview")
	public ModelAndView catboardview(ModelAndView mv, CatBoardVO bv) {
		
		service.countUp(bv);
		
		bv = service.selectOne(bv);
		
		if(bv != null) {
			mv.addObject("view",bv);
			mv.setViewName("board/catboardview");
		}else {
			mv.setViewName("catboard");
		}
		
		mv.setViewName("jsonView");
		return mv;
		
	} // catboardview
	
	
} // class
