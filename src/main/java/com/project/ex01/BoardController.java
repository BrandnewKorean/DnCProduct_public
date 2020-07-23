package com.project.ex01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import searchCriteria.PageMaker;
import searchCriteria.Search;
import service.CatBoardService;
import vo.CatBoardVO;

@Controller
public class BoardController {
	@Autowired
	CatBoardService service;
	
	@RequestMapping(value="catboard")
	public ModelAndView catboard(Search search, HttpServletRequest request, ModelAndView mv, @RequestParam(defaultValue = "list") String code) throws ParseException {
		System.out.println(code);
		
		search.setSnoEno();
		
		List<CatBoardVO> list = service.searchList(search);
		System.out.println(list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setSearch(search);
		pageMaker.setTotalRow(service.searchRowCount(search));
		
		Date current = new Date();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		for(int i=0;i<list.size();i++) {
			Date reg = fm.parse(list.get(i).getRegdate());
			long diff = current.getTime() - reg.getTime();
			long diffsec = (diff / 1000 % 60);
			long diffmin = (diff / (60 * 1000) % 60);
			long diffhour = (diff / (60 * 60 * 1000));
			long diffday = (diff / (24*60*60*1000));
			
			if(diffday <= 0) {
				if(diffhour <= 0) {
					if(diffmin <= 0) {
						if(diffsec < 30) {
							list.get(i).setRegdate("방금");
						}else {
							list.get(i).setRegdate(diffsec+"초 전");
						}
					}else {
						list.get(i).setRegdate(diffmin+"분 전");
					}
				}else {
					list.get(i).setRegdate(diffhour+"시간 전");
				}
			}else {
				if(diffday > 0 && diffday < 7) {
					list.get(i).setRegdate(diffday+"일 전");
				}else {
					SimpleDateFormat fm2 = new SimpleDateFormat("yyyy/MM/dd");
					Date r = fm2.parse(list.get(i).getRegdate());
					String regdate = fm2.format(r);
					list.get(i).setRegdate(regdate);
				}
			}
		}
		
		if(code.equals("image")) request.getSession().setAttribute("view", true);
		else request.getSession().setAttribute("view", false);
		
		mv.addObject("pageMaker",pageMaker);
		mv.addObject("list",list);
		mv.setViewName("cat/board/catboard");
		return mv;
	}// catboardpage
	
	@RequestMapping(value = "catboardinsertf")
	public ModelAndView catboardinsertf(ModelAndView mv) {
		mv.setViewName("cat/board/catboardinsertf");
		return mv;	
	}
	
	@RequestMapping(value="catboardinsert")
	public ModelAndView catboardinsert(HttpServletRequest request, ModelAndView mv, CatBoardVO bv) {
		String id = (String)request.getSession().getAttribute("logID");
		boolean view = (boolean)request.getSession().getAttribute("view");
		
		Date current = new Date();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		bv.setRegdate(fm.format(current));
		
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
		mv.addObject("view", view);
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
		
		Date current = new Date();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		bv.setRegdate(fm.format(current));
		
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
} // class
