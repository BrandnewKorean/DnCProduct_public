package com.project.ex01;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import searchCriteria.StorePageMaker;
import searchCriteria.StoreSearch;
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
	public ModelAndView catstoreview(StoreSearch search, ModelAndView mv) {
		System.out.println(search);
		if(search.getKeyword() == null) search.setKeyword("");
		search.setPerPage(11);
		search.setSnoEno();
		
		List<CatStoreVO> list = service.searchList(search);
		Map<Integer,List<ProductImageVO>> productimageMap = new HashMap<>();
		Map<Integer,ProductVO> productMap = new HashMap<>();
		
		StorePageMaker pageMaker = new StorePageMaker();
		pageMaker.setSearch(search);
		pageMaker.setTotalRow(service.searchRowCount(search));
		
		for(int i=0;i<list.size();i++) {
			ProductVO pv = new ProductVO();
			ProductImageVO piv = new ProductImageVO();
			pv.setProductcode(list.get(i).getProductcode());
			piv.setProductcode(list.get(i).getProductcode());
			productimageMap.put(list.get(i).getSeq(), piservice.selectList(piv));
			productMap.put(list.get(i).getSeq(), pservice.selectOne(pv));
		}
		
		mv.addObject("search", search);
		mv.addObject("pageMaker", pageMaker);
		mv.addObject("productMap", productMap);
		mv.addObject("productimageMap",productimageMap);
		mv.addObject("list", list);
		mv.setViewName("cat/store/CatStoreView");
		return mv;
	}
}
