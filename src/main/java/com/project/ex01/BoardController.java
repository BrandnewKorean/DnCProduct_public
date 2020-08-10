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
import service.CatBoardService;
import service.CatBoardUploadService;
import vo.CatBoardCommentVO;
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
		
		if(code.equals("image")) {
			Map<Integer,List<CatBoardUploadVO>> uploadlistMap = new HashMap<>();
			for(int i=0;i<list.size();i++) {
				uploadlistMap.put(list.get(i).getSeq(), uservice.selectList(list.get(i).getSeq()));
			}
			mv.addObject("uploadlistMap",uploadlistMap);
			request.getSession().setAttribute("view", true);
		}
		else request.getSession().setAttribute("view", false);
		
		mv.addObject("pageMaker",pageMaker	);
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
	public ModelAndView catboardinsert(HttpServletRequest request, @RequestParam("files") List<MultipartFile> files, ModelAndView mv, CatBoardVO bv) throws IllegalStateException, IOException {
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
		service.countUp(bv);
		bv=service.selectOne(bv);
		
		//여기서부터
		List<CatBoardCommentVO> comment = cservice.selectList(bv.getSeq());
		
		mv.addObject("comment", comment);
		
		//여기까지 추가
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
