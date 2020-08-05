package com.project.ex01;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import searchCriteria.PageMaker;
import searchCriteria.Search;
import service.CatBoardCommentService;
import service.CatBoardImageUploadService;
import service.CatBoardService;
import vo.CatBoardCommentVO;
import vo.CatBoardImageUploadVO;
import vo.CatBoardVO;

@Controller
public class BoardController {
	@Autowired
	CatBoardService service;
	
	@Autowired
	CatBoardCommentService cservice;
	
	@Autowired
	CatBoardImageUploadService uservice;
	
	@RequestMapping(value="commentdelete")
	public ModelAndView commentdelete(HttpServletRequest request,ModelAndView mv, CatBoardCommentVO bcv) {
		
		//System.out.println("this is counter ="+counter);
		HttpSession session = request.getSession(false);
		
		if(session!=null && session.getAttribute("logID") != null) {
			int counter=cservice.delete(bcv);
			if(counter>0) {
				mv.addObject("bcode",0);
			}else{
				mv.addObject("bcode",1);
			}
		}else {
			mv.addObject("bcode",2);
		}
		
		service.updatecomments(bcv.getSeq());
		
		mv.setViewName("jsonView");
		return mv;
	}
	
	@RequestMapping(value="commentupdate")
	public ModelAndView commentupdate(HttpSession session, ModelAndView mv, CatBoardCommentVO bcv) {
		int counter = cservice.update(bcv);
		System.out.println("this is counter = "+counter);		
		//id 받아오기
		String id = (String)session.getAttribute("logID");
		
		if(id!=null) {
			if(counter>0) {
				mv.addObject("code",0);
			}else{
				mv.addObject("code",1);
			}
		}else {
			mv.addObject("code",2);
		}
		mv.setViewName("jsonView");
		return mv;
	}//commentupdate
	
//	@RequestMapping(value="commentupdatef")
//	public ModelAndView commentupdatef (ModelAndView mv, CatBoardCommentVO bcv) {
//		bcv=cservice.selectOne(bcv);
//		
//		mv.addObject("dncupdate", bcv);
//		mv.setViewName("cat/board/catboardview");
//		return mv;
//	}//catboardupdatef()
	
	@RequestMapping(value="writecomment", method= RequestMethod.GET)
	public ModelAndView writecomment(HttpSession session, ModelAndView mv, CatBoardCommentVO bcv) {
		String id=(String)session.getAttribute("logID");
		
		if(id != null) {
			bcv.setId(id);
			int ccount = cservice.insert(bcv);
			int count=service.updatecomments(bcv.getSeq());
			if(ccount>0 && count>0) {
				mv.addObject("code", 0); // 정상 등록
			}else if(ccount>0) {
				mv.addObject("code", 1); // 댓글 등록 안됬을 경우
			}else if(count>0){
				mv.addObject("code", 2); // comments 숫자 올리기 실패
			}else {
				mv.addObject("code", 3); // 둘다 실패
			}
		}else {
			mv.addObject("code", 4); // 로그인 안되있을 때
		}
		
		mv.setViewName("jsonView");
		return mv;
	}
	
	@RequestMapping(value="listcri")
	public ModelAndView catboardsearch(ModelAndView mv, Search search) {
		search.setSnoEno();
		mv.addObject("dnc",service.searchList(search));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setSearch(search);
		
		pageMaker.setTotalRow(service.searchRowCount(search));
		
		mv.addObject("pageMaker",pageMaker);
		
		mv.setViewName("cat/board/catboard");
		return mv;
	}
	
	
	@RequestMapping(value="catboard")
	public ModelAndView catboard(Search search, HttpServletRequest request, ModelAndView mv, @RequestParam(defaultValue = "list") String code) throws ParseException {
		search.setSnoEno();
		List<CatBoardVO> list = service.searchList(search);
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
	
	@RequestMapping(value="catboardinsert", method=RequestMethod.POST)
	public ModelAndView catboardinsert(HttpServletRequest request,@RequestParam("files") List<MultipartFile> files ,ModelAndView mv, MultipartFile file, CatBoardVO bv) 
			throws IllegalStateException, IOException {
		String id = (String)request.getSession().getAttribute("logID");
		boolean view = (boolean)request.getSession().getAttribute("view");
		// list형태로 볼지, image형태로 볼지
		
		CatBoardImageUploadVO uvo = new CatBoardImageUploadVO();
		// 자동주입하지 않는 이유는 image upload를 하지 않을 수도 있기 때문
		
		int count;
		
		Date current = new Date();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		//SimpleDateFormat 라는 클래스를 사용하여, string 타입으로 시간을 사용하기 위해서
		
		bv.setRegdate(fm.format(current));
		// regdate에 현재 시간을 넣어줌
		
		String root_path = request.getSession().getServletContext().getRealPath("/");
		String attach_path = "resources/catboardupload/";
		
		if(id != null) {
			bv.setSeq(service.insertseq());
			bv.setId(id);
			//session에서 받아온  id 넣어주기
			count=service.insert(bv);
			// 게시판에서 insert
			if(count>0) {
				if(!files.isEmpty()) {
					String route = "C:\\Users\\yong\\git\\dnc1\\src\\main\\webapp\\resources\\catboardupload\\";
					for(int i=0; i<files.size();i++) {
						String filename=bv.getSeq()+"_"+files.get(i).getOriginalFilename();
						uvo.setSeq(bv.getSeq());
						uvo.setUploadfile(files.get(i).getOriginalFilename());
					    //files.get(i).transferTo(new File(route+filename));
						files.get(i).transferTo(new File(root_path+attach_path+filename));
						if(uservice.insert(uvo) > 0) {
							mv.addObject("bcode",0);
						}else {
							mv.addObject("bcode",1);
						}
					}//for
				}else{
					mv.addObject("bcode",0);
				}//if files.empty
			}//if count
		}else {
			mv.addObject("bcode",2);
			//id 값이 없으면 
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

		//여기서부터
		List<CatBoardCommentVO> comment = cservice.selectList(bv.getSeq());
		List<CatBoardImageUploadVO> upload = uservice.selectList(bv.getSeq());
		
		mv.addObject("comment", comment);
		mv.addObject("upload",upload);
		//여기까지 추가
		
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
