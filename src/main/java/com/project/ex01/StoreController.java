package com.project.ex01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StoreController {
	
	@RequestMapping(value = "storemain")
	public ModelAndView storemain(ModelAndView mv) {
		mv.setViewName("cat/store/StoreMain");
		return mv;
	}
}
