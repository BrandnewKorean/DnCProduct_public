package com.project.ex01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LocationController {
	
	@RequestMapping(value = "/hospital")
	public ModelAndView hospital(ModelAndView mv) {
		mv.setViewName("cat/location/hospital");
		return mv;
	}// hospital
	
	

} // class
