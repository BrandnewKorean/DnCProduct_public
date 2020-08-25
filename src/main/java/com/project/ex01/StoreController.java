package com.project.ex01;

import java.text.DecimalFormat;
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
		if(search.getKeyword() == null) search.setKeyword("");
		
		search.setPerPage(11);
		search.setSnoEno();
		
		List<CatStoreVO> list = service.searchList(search);
		Map<Integer,List<ProductImageVO>> productimageMap = new HashMap<>();
		Map<Integer,ProductVO> productMap = new HashMap<>();
		
		StorePageMaker pageMaker = new StorePageMaker();
		pageMaker.setSearch(search);
		pageMaker.setTotalRow(service.searchRowCount(search));
		
		DecimalFormat fm = new DecimalFormat("###,###");
		Map<Integer,String> priceMap = new HashMap<>();
		
		for(int i=0;i<list.size();i++) {
			ProductVO pv = new ProductVO();
			ProductImageVO piv = new ProductImageVO();
			
			pv.setProductcode(list.get(i).getProductcode());
			piv.setProductcode(list.get(i).getProductcode());
			
			List<ProductImageVO> imagelist = piservice.selectList(piv);
			for(int j=0;j<imagelist.size();j++) {
				if(imagelist.get(j).getIsmain() && j != 0) {
					ProductImageVO temp1 = imagelist.get(j);
					ProductImageVO temp2 = imagelist.get(0);
					imagelist.set(0, temp1);
					imagelist.set(j, temp2);
				}
			}
			
			priceMap.put(list.get(i).getSeq(), fm.format(list.get(i).getPrice()));
			productimageMap.put(list.get(i).getSeq(), imagelist);
			productMap.put(list.get(i).getSeq(), pservice.selectOne(pv));
		}
		
		mv.addObject("search", search);
		mv.addObject("pageMaker", pageMaker);
		mv.addObject("productMap", productMap);
		mv.addObject("productimageMap",productimageMap);
		mv.addObject("priceMap", priceMap);
		mv.addObject("list", list);
		mv.setViewName("cat/store/CatStoreView");
		return mv;
	}
	
	@RequestMapping(value = "products")
	public ModelAndView products(ModelAndView mv, CatStoreVO cs) {
		System.out.println(cs);
		ProductVO pv = new ProductVO();
		ProductImageVO piv = new ProductImageVO();
		pv.setProductcode(cs.getProductcode());
		piv.setProductcode(cs.getProductcode());
		
		List<ProductImageVO> imagelist = piservice.selectList(piv);
		for(int i=0;i<imagelist.size();i++) {
			if(imagelist.get(i).getIsmain() && i != 0) {
				ProductImageVO temp1 = imagelist.get(i);
				ProductImageVO temp2 = imagelist.get(0);
				imagelist.set(0, temp1);
				imagelist.set(i, temp2);
			}
		}
		pv = pservice.selectOne(pv);
		cs = service.selectOne(cs);
		
		DecimalFormat fm = new DecimalFormat("###,###");
		
		mv.addObject("price", fm.format(cs.getPrice()));
		mv.addObject("pv", pv);
		mv.addObject("imagelist",imagelist);
		mv.setViewName("cat/store/Products");
		return mv;
	}
	
	@RequestMapping(value = "productimage")
	public ModelAndView productimage(ModelAndView mv, ProductImageVO piv) {
		List<ProductImageVO> list = piservice.selectList(piv);
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getIsmain() && i != 0) {
				ProductImageVO temp1 = list.get(i);
				ProductImageVO temp2 = list.get(0);
				list.set(0, temp1);
				list.set(i, temp2);
			}
		}
		mv.addObject("list", list);
		mv.setViewName("jsonView");
		return mv;
	}
	
	@RequestMapping(value = "searchresult1")
	public ModelAndView searchresult1(StoreSearch search, ModelAndView mv) {
		search.setSnoEno();
		search.setGroup1("식료품");
		
		List<CatStoreVO> list = service.searchList(search);
		Map<Integer,ProductVO> productMap = new HashMap<>();
		Map<Integer,List<ProductImageVO>> productimageMap = new HashMap<>();
		
		StorePageMaker pageMaker = new StorePageMaker();
		pageMaker.setSearch(search);
		pageMaker.setTotalRow(service.searchRowCount(search));
		
		for(int i=0;i<list.size();i++) {
			ProductVO pv = new ProductVO();
			ProductImageVO piv = new ProductImageVO();
			
			pv.setProductcode(list.get(i).getProductcode());
			piv.setProductcode(list.get(i).getProductcode());
			
			List<ProductImageVO> imagelist = piservice.selectList(piv);
			for(int k=0;k<imagelist.size();k++) {
				if(imagelist.get(k).getIsmain() && k != 0) {
					ProductImageVO temp1 = imagelist.get(k);
					ProductImageVO temp2 = imagelist.get(0);
					imagelist.set(0, temp1);
					imagelist.set(k, temp2);
				}
			}
			
			productMap.put(list.get(i).getSeq(), pservice.selectOne(pv));
			productimageMap.put(list.get(i).getSeq(), piservice.selectList(piv));
		}
		
		mv.addObject("list", list);
		mv.addObject("productMap", productMap);
		mv.addObject("productimageMap", productimageMap);
		mv.addObject("pageMaker", pageMaker);
		mv.addObject("search", search);
		mv.setViewName("cat/store/SearchResult1");
		return mv;
	}
	
	@RequestMapping(value = "searchresult")
	public ModelAndView searchresult(StoreSearch search, ModelAndView mv) {
		mv.addObject("search", search);
		mv.setViewName("cat/store/SearchResult");
		return mv;
	}
}