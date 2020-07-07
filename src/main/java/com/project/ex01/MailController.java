package com.project.ex01;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MailController {

	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value="mailSending")
	public ModelAndView mailSending (ModelAndView mv, String address) {
		Random random = new Random();
		String number="";
		
		for(int i=0; i<5;i++) {
			number+=String.valueOf(random.nextInt(10));
		}
		
		String setfrom ="DnCProductSystem@gmail.com";
		String tomail=address;
		String title="[DnCProduct]인증번호를 보내드립니다";
		String content="<h1>DNCProduct</h1>";
		
		return mv;
	}//mailSending
}
