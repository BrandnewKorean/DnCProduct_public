package com.project.ex01;

import java.util.List;

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
		
		if(list != null) {
			mv.addObject("dnc",list);
		}else {
			mv.addObject("bcode",false); 
			// client에게 message 전달해주는 방식을 alert으로 하기 위해 code 부여
		}
		mv.setViewName("cat/board/catboard");
		return mv;
	} // boardlist
	
	
	
	
	@RequestMapping(value="catboardinsert")
	public ModelAndView catboardinsert(ModelAndView mv, CatBoardVO bv) {
		
		if(service.insert(bv) > 0) {
			// 글 등록 성공 -> list 출력
			mv.setViewName("redirect:catboard");
		}else {
			// 글 등록 실패 -> 다시 시도하기
			mv.addObject("bcode",false);
		}
		mv.setViewName("jsonView");
		return mv;
		
	} // catboardinsert
	
	
} // class
