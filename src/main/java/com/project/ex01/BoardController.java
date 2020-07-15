package com.project.ex01;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public ModelAndView catboardview(HttpServletRequest request,ModelAndView mv, CatBoardVO bv) {
		//글번호로 글검색
		service.countUp(bv);
		bv=service.selectOne(bv);
		mv.addObject("bv", bv);
		mv.setViewName("cat/board/catboardview");
		return mv;
		
	}//catboardview
	
	@RequestMapping(value="catboardupdatef")
	public ModelAndView catboardupdatef (ModelAndView mv, CatBoardVO bv) {
		bv=service.selectOne(bv);
		
		mv.addObject("dnc", bv);
		mv.setViewName("cat/board/catboardupdatef");
		
		return mv;
	}//catboardupdatef()
	
	
	@RequestMapping(value="catboardupdate")
	public ModelAndView catboardupdate(HttpServletRequest request,ModelAndView mv, CatBoardVO bv) {
		String id=(String)request.getSession().getAttribute("logID");
		
		if(id!=null) {
			if(service.update(bv)>0) {
				mv.addObject("bcode",0);
			}else{
				mv.addObject("bcode",1);
			}
		}else {
			mv.addObject("bcode",2);
		}
		
		mv.setViewName("jsonView");
		return mv;
	}
	
	@RequestMapping(value="catboarddelete")
	public ModelAndView catboarddelete(HttpServletRequest request,ModelAndView mv, CatBoardVO bv) {
		HttpSession session = request.getSession(false);
		if(session!=null && session.getAttribute("logID") != null) {
			if(service.delete(bv)>0) {
				mv.setViewName("redirect:catboard");
			}else{
				mv.setViewName("redirect:catboardview?seq="+bv.getSeq());
			}
		}else {
			mv.setViewName("redirect:catmain?seq="+bv.getSeq());
		}
		return mv;
	}
} // class
