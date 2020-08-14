package com.project.ex01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.CatStoreService;
import service.ProductImageService;
import service.ProductService;
import vo.CatStoreVO;
import vo.ProductImageVO;
import vo.ProductVO;

@Controller
public class StoreController {
	
	@Autowired
	CatStoreService service;
	
	@Autowired
	ProductService pservice;
	
	@Autowired
	ProductImageService piservice;
	
	@RequestMapping(value = "storemain")
	public ModelAndView storemain(ModelAndView mv) {
		mv.setViewName("cat/store/StoreMain");
		return mv;
	}
	
	@RequestMapping(value = "catstoreview")
	public ModelAndView catstoreview(ModelAndView mv, CatStoreVO cs) {
		System.out.println(cs);
		
		List<CatStoreVO> list = service.selectList(cs);
		Map<Integer,List<ProductImageVO>> productimageMap = new HashMap<>();
		Map<Integer,String> productnameMap = new HashMap<>();
		
		for(int i=0;i<list.size();i++) {
			ProductVO pv = new ProductVO();
			ProductImageVO piv = new ProductImageVO();
			
			
			
			piv.setProductcode(list.get(i).getProductcode());
			productimageMap.put(list.get(i).getSeq(), piservice.selectList(piv));
		}
		
		mv.addObject("productimageMap",productimageMap);
		mv.addObject("list", list);
		mv.setViewName("cat/store/CatStoreView");
		return mv;
	}
}
