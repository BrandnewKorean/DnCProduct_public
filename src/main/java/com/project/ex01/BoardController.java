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
import org.springframework.web.servlet.ModelAndView;

import service.CatBoardService;
import vo.CatBoardVO;
import vo.PageVO;

@Controller
public class BoardController {
	@Autowired
	CatBoardService service;
	
	@RequestMapping(value="catboard")
	public ModelAndView catboard(ModelAndView mv, PageVO<CatBoardVO> pvo) throws ParseException {
		// ** paging 1 **
		//1. paging 준비
		// DAO의 pagelist를 처리하기 위해 필요한 값을 계산
		// currentPage,startno,endno
		int currentPage=1;
		if(pvo.getCurrentPage()>1) {
			currentPage=pvo.getCurrentPage();
		}else {
			pvo.setCurrentPage(currentPage);
		}

		int startRowno=(currentPage-1)*pvo.getPerPage()+1;
		int endRowno=(startRowno+pvo.getPerPage())-1;
		pvo.setStartno(startRowno);
		pvo.setEndno(endRowno);
		
		//2) service
		//DB에서 필요한 값들을 set
		// 출력할 Row List, totalCount(totalRowCount)
		pvo=service.pageList(pvo);
		
		Date current = new Date();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		for(int i=0;i<pvo.getList().size();i++) {
			Date reg = fm.parse(pvo.getList().get(i).getRegdate());
			long diff = current.getTime() - reg.getTime();
			long diffsec = (diff / 1000 % 60);
			long diffmin = (diff / (60 * 1000) % 60);
			long diffhour = (diff / (60 * 60 * 1000));
			long diffday = (diff / (24*60*60*1000));
			
			if(diffday <= 0) {
				if(diffhour <= 0) {
					if(diffmin <= 0) {
						if(diffsec < 30) {
							pvo.getList().get(i).setRegdate("방금");
						}else {
							pvo.getList().get(i).setRegdate(diffsec+"초 전");
						}
					}else {
						pvo.getList().get(i).setRegdate(diffmin+"분 전");
					}
				}else {
					pvo.getList().get(i).setRegdate(diffhour+"시간 전");
				}
			}else {
				if(diffday > 0 && diffday < 7) {
					pvo.getList().get(i).setRegdate(diffday+"일 전");
				}else {
					SimpleDateFormat fm2 = new SimpleDateFormat("yyyy/MM/dd");
					Date r = fm2.parse(pvo.getList().get(i).getRegdate());
					String regdate = fm2.format(r);
					pvo.getList().get(i).setRegdate(regdate);
				}
			}
		}
		
		//3) 결과처리
		// totalCount 를 이용해서 totalPageNo 계산
		// totalCount 가 70이면 totalPageNo는? (1page당 5개씩 출력)
		//  73/5=14, 나머지가 있으면 +1
		int totalPageNo = pvo.getTotalCount()/pvo.getPerPage();
		if(pvo.getTotalCount()%pvo.getPerPage() >0)
			totalPageNo+=1;
		
		//  ** paging 2 **
		//sPageNo, ePageNo 계산
		//필요한 값 : currentPage, perPageNo
		//유형
		//	1) 항상 현재 Page가 중앙에 위치할때
		//		startPage : currentPage - perPageNo/2
		//		endPage : currentPage - perPageNo/2
		//
		//	2) naver 카페 글, 11번가 상품 리스트 type
		//		startPage : (((currentPage-1)/perPageNo)*perPageNo)+1
		
		int startPageNo=((currentPage-1)/pvo.getPerPageNO())*pvo.getPerPageNO()+1;
		int endPageNo=startPageNo+pvo.getPerPageNO()-1;
		
		if(endPageNo>totalPageNo) endPageNo=totalPageNo;
		
		mv.addObject("startPage",startPageNo);
		mv.addObject("endPage",endPageNo);
		mv.addObject("perPageNO",pvo.getPerPageNO());
		
		mv.addObject("totalPageNo",totalPageNo);
		mv.addObject("currentPage",currentPage);
		
		mv.addObject("list",pvo.getList());
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
