package com.project.ex01;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import service.CatBoardHeartService;
import service.CatBoardService;
import service.CatBoardUploadService;
import vo.CatBoardCommentVO;
import vo.CatBoardHeartVO;
import vo.CatBoardUploadVO;
import vo.CatBoardVO;

@Controller
public class BoardController {
	@Autowired
	CatBoardService service;
	
	@Autowired
	CatBoardCommentService cservice;
	
	@Autowired
	CatBoardUploadService uservice;
	
	@Autowired
	CatBoardHeartService hservice;
	
	@RequestMapping(value="likeCheck")
	public ModelAndView likeCheck(HttpServletRequest request,ModelAndView mv,CatBoardHeartVO bhv) {
		
		//1. session에 있는 ID 값 가져오기
		HttpSession session = request.getSession(false);
		if(session !=null && session.getAttribute("logID")!=null) {
			bhv.setId((String) session.getAttribute("logID"));
		//2. 
//			hservice ==> 좋아요  service 
//				selectlike()   catboard_heart에서 seq와 id가 같은 많은 것을 검색하라 
//				likeCheck()    catboard에 있는 heart 값을 수정해라 
//				likeinsert()   coatboard_heart에 seq 값과 id 값을 삽입하라
//				likedelete()   seq와 id 값을 삭제하라
			
			//code 0 ==> 좋아요삭제성공
			//code 1==> 좋아요 삭제 실패
			
			//code 2==> 좋아요 추가 성공
			//code 3==> 좋아요 추가 실패
			
			//code 4==> 로그인 실패
			
			//default ==> error
//			
			if(hservice.selectlike(bhv) != null) {
				if(hservice.likedelete(bhv)>0) {
					System.out.println("좋아요 삭제");
					mv.addObject("code", 0);
				}else {
					mv.addObject("code", 1);
				}
			}else {
				if(hservice.likeinsert(bhv)>0) {
					System.out.println("좋아요추가");
					mv.addObject("code", 2);
				}else {
					mv.addObject("code", 3);
				}
			}
		}else {
			mv.addObject("code",4);
		}
		hservice.likeCheck(bhv);
		mv.setViewName("jsonView");
		return mv;
	}
	
	@RequestMapping(value="commentdelete")
	public ModelAndView commentdelete(HttpServletRequest request,ModelAndView mv, CatBoardCommentVO bcv) {
		HttpSession session = request.getSession(false);
		if(session!=null && session.getAttribute("logID") != null) {
			
			if(cservice.delete(bcv)>0) {
				mv.addObject("code",0);
			}else{
				mv.addObject("code",1);
			}
		}else {
			mv.addObject("code",2);
		}
		
		service.updatecomments(bcv.getSeq());
		
		mv.setViewName("jsonView");
		return mv;
	}
	
	@RequestMapping(value="commentupdate")
	public ModelAndView commentupdate(HttpServletRequest request,ModelAndView mv, CatBoardCommentVO bcv) {
		
		int counter = cservice.update(bcv);
		String id=(String)request.getSession().getAttribute("logID");
		
		
		
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
	}	
	
	@RequestMapping(value = "writecomment", method = RequestMethod.GET)
	public ModelAndView writecomment(HttpSession session, CatBoardCommentVO bcv, ModelAndView mv) {
		String id = (String)session.getAttribute("logID");
		// getAttribute("id") 확인
		bcv.setId(id);
	
		if(id != null) {
			bcv.setId(id);
			System.out.println(bcv);
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
	} // writecomment
	
	@RequestMapping(value="catboard")
	public ModelAndView catboard(Search search, HttpServletRequest request, ModelAndView mv, @RequestParam(defaultValue = "list") String code) throws ParseException {
		if(code.equals("image")) {
			search.setPerPage(16);
		}else {
			search.setPerPage(15);
		}
		search.setSnoEno();
		
		List<CatBoardVO> list = service.searchList(search);
		System.out.println("this is list =>" +list);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setSearch(search);
		pageMaker.setTotalRow(service.searchRowCount(search));
		
		
		// Map은 interface , HashMap은 class이다
		// key =seq , value = seq에 해당하는 uploadlist
		if(code.equals("image")) {
			Map<Integer,List<CatBoardUploadVO>> uploadlistMap = new HashMap<>();
			for(int i=0;i<list.size();i++) {
				uploadlistMap.put(list.get(i).getSeq(), uservice.selectList(list.get(i).getSeq()));
			}
			mv.addObject("uploadlistMap",uploadlistMap);
			request.getSession().setAttribute("view", true);
		}
		else request.getSession().setAttribute("view", false);
		
		//id값 가져오기
		//list에서 seq값을 가져와서 boardheartvo 에 넣기
		//session에서 ID값을 가져와서 boardheartvo 에 넣기
		
		//likeMap을 사용한 이유
		//key : seq   value: true & false
		//seq 3은 false 
		//seq 2는 true
		
		//==> seq 값마다 좋아요가 off될 수도 on 될 수도 있어서
		// hashmap을 사용
		
		
		
		String id = (String)request.getSession().getAttribute("logID");
		Map<Integer,Boolean> likeMap = new HashMap<>();
		for(int i=0;i<list.size();i++) {
			CatBoardHeartVO bhv = new CatBoardHeartVO();
			bhv.setSeq(list.get(i).getSeq());
			bhv.setId(id);
			bhv = hservice.selectlike(bhv);
			if(bhv != null) {
				likeMap.put(list.get(i).getSeq(),true);
			}else {
				likeMap.put(list.get(i).getSeq(),false);
			}
		}
		
		mv.addObject("likeMap", likeMap);
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
	public ModelAndView catboardinsert(HttpServletRequest request, @RequestParam("files") List<MultipartFile> files, ModelAndView mv, CatBoardVO bv) throws IllegalStateException, IOException, ParseException {
		String id = (String)request.getSession().getAttribute("logID");
		boolean view = (boolean)request.getSession().getAttribute("view");
		
		CatBoardUploadVO uvo = new CatBoardUploadVO();
		int count;
		int uploadcount = 0;
		
		Date current = new Date();
		SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		
		bv.setRegdate(fm.format(current));
		
		String root_path = request.getSession().getServletContext().getRealPath("/");
		String attach_path = "resources/catboardupload/";
		
		if(id != null) {
			bv.setSeq(service.insertseq());
			bv.setId(id);
			count = service.insert(bv);
			if(count > 0) {
				if(!files.isEmpty()) {
					for(int i=0; i< files.size(); i++) {
						System.out.println(files.get(i).getOriginalFilename());
						String filename = bv.getSeq()+"_"+files.get(i).getOriginalFilename();
						uvo.setSeq(bv.getSeq());
						uvo.setUploadfile(files.get(i).getOriginalFilename());
						files.get(i).transferTo(new File(root_path+attach_path+filename));
						if(uservice.insert(uvo) > 0) {
							uploadcount++;
						}
					}//for
					if(uploadcount == files.size()) {
						mv.addObject("code", 0);
					}else {
						mv.addObject("code", 1);
					}
				}else {
					mv.addObject("code", 0);// if
				}
			}else {
				mv.addObject("code", 2);
			}
		}else {
			mv.addObject("code", 3);
		}
		mv.addObject("view", view);
		mv.setViewName("jsonView");
		return mv;
	} // catboardinsert
	
	@RequestMapping(value="catboardview")
	public ModelAndView catboardview(HttpServletRequest request,ModelAndView mv, CatBoardVO bv) {
		//글번호로 글검색
		
		//session에서 id값 가져오기
		//catboard와 달리 여기서는 map을 쓸 필요가 없음
		// 왜?? catboardview 에는 seq 값이 달라질 수 없음(seq가 고정)
		//그래서 boolean 으로 true & false 값만 확인하면 됨
		
		
		
		String id = (String)request.getSession().getAttribute("logID");
		
		Boolean islike;
		
		CatBoardHeartVO bhv = new CatBoardHeartVO();
		bhv.setSeq(bv.getSeq());
		bhv.setId(id);
		bhv = hservice.selectlike(bhv);
		if(bhv != null) {
			islike = true;
		}else {
			islike = false;
		}
		
		service.countUp(bv);
		bv=service.selectOne(bv);
		
		System.out.println("this is bv => " + bv);
		
		//여기서부터
		List<CatBoardCommentVO> comment = cservice.selectList(bv.getSeq());
		
		mv.addObject("comment", comment);
		
		//여기까지 추가
		mv.addObject("islike", islike);
		mv.addObject("bv", bv);
		mv.setViewName("cat/board/catboardview");
		return mv;
		
	}//catboardview
	
	@RequestMapping(value = "catboardmedia")
	public ModelAndView catboardmedia(ModelAndView mv, int seq) {
		List<CatBoardUploadVO> upload = uservice.selectList(seq);
		mv.addObject("upload",upload);
		mv.setViewName("jsonView");
		return mv;
	}
	
	@RequestMapping(value="catboardupdatef")
	public ModelAndView catboardupdatef (ModelAndView mv, CatBoardVO bv) {
		bv=service.selectOne(bv);
		
		mv.addObject("dnc", bv);
		mv.setViewName("cat/board/catboardupdatef");
		
		return mv;
	}//catboardupdatef()
	
	
	@RequestMapping(value="catboardupdate")
	public ModelAndView catboardupdate(HttpServletRequest request,ModelAndView mv, CatBoardVO bv) throws ParseException {
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
		System.out.println(bv);
		System.out.println(session.getAttribute("logID"));
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
