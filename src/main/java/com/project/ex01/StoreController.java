package com.project.ex01;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import searchCriteria.StorePageMaker;
import searchCriteria.StoreSearch;
import service.BasketService;
import service.CatStoreService;
import service.ProductImageService;
import service.ProductService;
import vo.BasketVO;
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
	
	@Autowired
	BasketService bservice;
	
	@RequestMapping(value = "top5")
	public ModelAndView top5(StoreSearch search, ModelAndView mv) {
		System.out.println(search);
		search.setPerPage(5);
		search.setSnoEno();
		List<CatStoreVO> list = service.searchList(search);
		mv.addObject("list",list);
		mv.setViewName("jsonView");
		return mv;
	}
	
	@RequestMapping(value = "storemain")
	public ModelAndView storemain(ModelAndView mv) {
		mv.setViewName("cat/store/StoreMain");
		return mv;
	}
	
	@RequestMapping(value = "catstoreview")
	public ModelAndView catstoreview(StoreSearch search, ModelAndView mv) {
		if(search.getOrder1() == null) {
			search.setOrder1("seq");
		}
		if(search.getOrder2() == null) {
			search.setOrder2("desc");
		}
		
		if(search.getKeyword() == null) search.setKeyword("");
		
		search.setPerPage(12);
		search.setSnoEno();
		System.out.println(search);
		
		// 전체 list 불러오기
		
		//42 개 중에 10개를 검색했습니다
		
		//1. 42개
			//searchrowcount
			
		
		//2. 10개 
			//searchList
		List<CatStoreVO> list = service.searchList(search);
		
		System.out.println("this is list = "+list);
		
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
		
		mv.addObject("cs", cs);
		mv.addObject("price", fm.format(cs.getPrice()));
		mv.addObject("pv", pv);
		mv.addObject("imagelist",imagelist);
		mv.setViewName("cat/store/Products");
		return mv;
	}
	
	
	@RequestMapping(value="catbasketform")
	public ModelAndView catbasketform(HttpServletRequest request,ModelAndView mv,BasketVO bkv) {
		HttpSession session = request.getSession(false);
		List<BasketVO> list = null;
		Map<Integer,ProductImageVO> imageMap = new HashMap<>();
		Map<Integer,CatStoreVO> storeMap = new HashMap<>();
		Map<Integer,ProductVO> productMap = new HashMap<>();
		
		CatStoreVO cv = new CatStoreVO();
		ProductVO pv = new ProductVO();
		ProductImageVO piv = new ProductImageVO();
		
		if(session !=null && session.getAttribute("logID")!=null) {
			bkv.setId((String)session.getAttribute("logID"));
			list = bservice.selectList(bkv);
			
			for(int i=0;i<list.size();i++) {
				cv.setSeq(list.get(i).getSeq());
				cv = service.selectOne(cv);
				storeMap.put(list.get(i).getSeq(), cv);
				
				pv.setProductcode(cv.getProductcode());
				pv = pservice.selectOne(pv);
				productMap.put(list.get(i).getSeq(), pv);
				
				piv.setProductcode(cv.getProductcode());
				
				ProductImageVO image = piservice.selectOne(piv);
				
				imageMap.put(list.get(i).getSeq(),image);
			}
		}
		
		mv.addObject("productMap", productMap);
		mv.addObject("imageMap", imageMap);
		mv.addObject("storeMap", storeMap);
		mv.addObject("list", list);
		mv.setViewName("cat/store/CatBasketForm"); // 위치
		return mv;
	}
	
	@RequestMapping(value="catbasket")
	public ModelAndView catbasket(HttpServletRequest request,ModelAndView mv, BasketVO bkv) {
		HttpSession session = request.getSession(false);
		if(session !=null && session.getAttribute("logID")!=null) {
			bkv.setId((String)session.getAttribute("logID"));
		
			if(bservice.selectOne(bkv) != null) {
				mv.addObject("code", 0);
			}else {
				if(bservice.insert(bkv)>0) {
					mv.addObject("code",1);
				}else {
					mv.addObject("code", 2);
				}
			}
		}else {
			mv.addObject("code", 3);
		}
		mv.setViewName("jsonView");
		return mv;
	}//catbasket()
	
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
		
		DecimalFormat fm = new DecimalFormat("###,###");
		Map<Integer,String> priceMap = new HashMap<>();
		
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
			
			priceMap.put(list.get(i).getSeq(), fm.format(list.get(i).getPrice()));
			productMap.put(list.get(i).getSeq(), pservice.selectOne(pv));
			productimageMap.put(list.get(i).getSeq(), piservice.selectList(piv));
		}
		
		mv.addObject("priceMap1",priceMap);
		mv.addObject("list1", list);
		mv.addObject("productMap1", productMap);
		mv.addObject("productimageMap1", productimageMap);
		mv.addObject("pageMaker1", pageMaker);
		mv.addObject("search1", search);
		mv.setViewName("cat/store/SearchResult1");
		return mv;
	}
	
	@RequestMapping(value = "searchresult2")
	public ModelAndView searchresult2(StoreSearch search, ModelAndView mv) {
		search.setSnoEno();
		search.setGroup1("배변/위생용품");
		
		List<CatStoreVO> list = service.searchList(search);
		Map<Integer,ProductVO> productMap = new HashMap<>();
		Map<Integer,List<ProductImageVO>> productimageMap = new HashMap<>();
		
		DecimalFormat fm = new DecimalFormat("###,###");
		Map<Integer,String> priceMap = new HashMap<>();
		
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
			
			priceMap.put(list.get(i).getSeq(), fm.format(list.get(i).getPrice()));
			productMap.put(list.get(i).getSeq(), pservice.selectOne(pv));
			productimageMap.put(list.get(i).getSeq(), piservice.selectList(piv));
		}
		
		mv.addObject("priceMap2",priceMap);
		mv.addObject("list2", list);
		mv.addObject("productMap2", productMap);
		mv.addObject("productimageMap2", productimageMap);
		mv.addObject("pageMaker2", pageMaker);
		mv.addObject("search2", search);
		mv.setViewName("cat/store/SearchResult2");
		return mv;
	}
	
	@RequestMapping(value = "searchresult3")
	public ModelAndView searchresult3(StoreSearch search, ModelAndView mv) {
		search.setSnoEno();
		search.setGroup1("미용용품");
		
		List<CatStoreVO> list = service.searchList(search);
		Map<Integer,ProductVO> productMap = new HashMap<>();
		Map<Integer,List<ProductImageVO>> productimageMap = new HashMap<>();
		
		DecimalFormat fm = new DecimalFormat("###,###");
		Map<Integer,String> priceMap = new HashMap<>();
		
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
			
			priceMap.put(list.get(i).getSeq(), fm.format(list.get(i).getPrice()));
			productMap.put(list.get(i).getSeq(), pservice.selectOne(pv));
			productimageMap.put(list.get(i).getSeq(), piservice.selectList(piv));
		}
		
		mv.addObject("priceMap3",priceMap);
		mv.addObject("list3", list);
		mv.addObject("productMap3", productMap);
		mv.addObject("productimageMap3", productimageMap);
		mv.addObject("pageMaker3", pageMaker);
		mv.addObject("search3", search);
		mv.setViewName("cat/store/SearchResult3");
		return mv;
	}
	
	@RequestMapping(value = "searchresult4")
	public ModelAndView searchresult4(StoreSearch search, ModelAndView mv) {
		search.setSnoEno();
		search.setGroup1("생활용품");
		
		List<CatStoreVO> list = service.searchList(search);
		Map<Integer,ProductVO> productMap = new HashMap<>();
		Map<Integer,List<ProductImageVO>> productimageMap = new HashMap<>();
		
		DecimalFormat fm = new DecimalFormat("###,###");
		Map<Integer,String> priceMap = new HashMap<>();
		
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
			
			priceMap.put(list.get(i).getSeq(), fm.format(list.get(i).getPrice()));
			productMap.put(list.get(i).getSeq(), pservice.selectOne(pv));
			productimageMap.put(list.get(i).getSeq(), piservice.selectList(piv));
		}
		
		mv.addObject("priceMap4",priceMap);
		mv.addObject("list4", list);
		mv.addObject("productMap4", productMap);
		mv.addObject("productimageMap4", productimageMap);
		mv.addObject("pageMaker4", pageMaker);
		mv.addObject("search4", search);
		mv.setViewName("cat/store/SearchResult4");
		return mv;
	}
	
	@RequestMapping(value = "searchresult")
	public ModelAndView searchresult(StoreSearch search, ModelAndView mv) {
		mv.addObject("search", search);
		mv.setViewName("cat/store/SearchResult");
		return mv;
	}
	
}