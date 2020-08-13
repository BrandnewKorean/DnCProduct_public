package com.project.ex01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LocationController {
	
	@RequestMapping(value="location")
	public ModelAndView animalhospital(ModelAndView mv) {
		mv.setViewName("cat/location/location");
		return mv;
	} // location

} // class
