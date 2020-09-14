package com.project.ex01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import service.DiaryService;
import service.DiaryUploadService;
import vo.DiaryUploadVO;
import vo.DiaryVO;

@Controller
public class DiaryController {
	
	@Autowired
	DiaryService service;
	@Autowired
	DiaryUploadService uservice;
	
	@RequestMapping(value = "diaryf")
	public ModelAndView diaryf(ModelAndView mv) {
		mv.setViewName("cat/diary/DiaryForm");
		return mv;
	}
	
	@RequestMapping(value = "diary")
	public ModelAndView diary(HttpServletRequest request, ModelAndView mv, DiaryVO dv) {
		String id = (String)request.getSession().getAttribute("logID");
		DiaryUploadVO duv = new DiaryUploadVO();
		
		List<DiaryUploadVO> uploadlist;
		
		if(id != null) {
			dv.setId(id);
			dv = service.selectOne(dv);
			if(dv != null) {
				duv.setWdate(dv.getWdate());
				duv.setId(id);
				uploadlist = uservice.selectList(duv);
				mv.addObject("uploadlist", uploadlist);
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

	@RequestMapping(value = "iswrited")
	public ModelAndView iswrited(HttpServletRequest request, ModelAndView mv, DiaryVO dv) {
		String id = (String)request.getSession().getAttribute("logID");
		List<String> writed = new ArrayList<String>();
		List<DiaryVO> list;
		
		if(id != null) {
			dv.setId(id);
			list = service.selectList(dv);
			for(int i=0;i<list.size();i++) {
				if(list.get(i) != null) {
					writed.add(list.get(i).getWdate());
				}
			}
		}else {
			writed = null;
		}
		mv.addObject("writed", writed);
		mv.setViewName("jsonView");
		return mv;
	}
	
	@RequestMapping(value = "diarywrite", method=RequestMethod.POST)
	public ModelAndView diarywrite(HttpServletRequest request, @RequestParam("files") List<MultipartFile> files, ModelAndView mv, DiaryVO dv) throws IllegalStateException, IOException {
		String id = (String)request.getSession().getAttribute("logID");
		DiaryUploadVO duv = new DiaryUploadVO();
		
		int count;
		
		System.out.println(request.getSession().getServletContext().getRealPath("/"));
		String root_path = request.getSession().getServletContext().getRealPath("/");
		String attach_path = "resources/diaryupload/";
		
		if(id != null) {
			dv.setId(id);
			dv.setSeq(service.insertseq());
			count = service.insert(dv);
			if(count > 0) {
				if(!files.isEmpty()) {
					for(int i=0;i<files.size();i++) {
						String filename = dv.getWdate()+"_"+dv.getId()+"_"+files.get(i).getOriginalFilename();
						duv.setSeq(dv.getSeq());
						duv.setId(dv.getId());
						duv.setWdate(dv.getWdate());
						duv.setFilename(files.get(i).getOriginalFilename());
						System.out.println(duv);
						files.get(i).transferTo(new File(root_path+attach_path+filename));
						if(uservice.insert(duv) > 0) {
							System.out.println("insert upload 0");
							mv.addObject("code", 0);
						}else {
							System.out.println("insert upload 1");
							mv.addObject("code", 1);
						}
					}
				}else {
					mv.addObject("code", 0);
				}
			}else {
				mv.addObject("code", 2);
			}
		}else {
			mv.addObject("code", 3);
		}
		
		mv.setViewName("jsonView");
		return mv;
	}
	
	@RequestMapping(value = "diarydelete")
	public ModelAndView diarydelete(HttpServletRequest request, ModelAndView mv, DiaryVO dv) {
		String id = (String)request.getSession().getAttribute("logID");
		int count;
		
		if(id != null) {
			dv.setId(id);
			count = service.delete(dv);
			if(count > 0) {
				mv.addObject("code", 0);
			}else {
				mv.addObject("code", 1);
			}
		}else {
			mv.addObject("code", 2);
		}
		
		mv.setViewName("jsonView");
		return mv;
	}
	
	@RequestMapping(value = "diaryupdate")
	public ModelAndView diaryupdate(HttpServletRequest request, @RequestParam("files") List<MultipartFile> files, ModelAndView mv, DiaryVO dv) throws IllegalStateException, IOException {
		String id = (String)request.getSession().getAttribute("logID");
		DiaryUploadVO duv = new DiaryUploadVO();
		int count;
		
		System.out.println(request.getSession().getServletContext().getRealPath("/"));
		String root_path = request.getSession().getServletContext().getRealPath("/");
		String attach_path = "resources/diaryupload/";
		
		if(id != null) {
			dv.setId(id);
			count = service.update(dv);
			if(count > 0) {
				if(!files.isEmpty()) {
					duv.setSeq(dv.getSeq());
					duv.setWdate(dv.getWdate());
					duv.setId(id);
					uservice.delete(duv);
					for(int i=0;i<files.size();i++) {
						String filename = dv.getWdate()+"_"+dv.getId()+"_"+files.get(i).getOriginalFilename();
						duv.setFilename(files.get(i).getOriginalFilename());
						files.get(i).transferTo(new File(root_path+attach_path+filename));
						if(uservice.insert(duv) > 0) {
							mv.addObject("code", 0);
						}else {
							mv.addObject("code", 1);
						}
					}
				}else {
					mv.addObject("code", 0);
				}
			}else {
				mv.addObject("code", 1);
			}
		}else {
			mv.addObject("code", 2);
		}
		
		mv.setViewName("jsonView");
		return mv;
	}
}