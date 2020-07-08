package com.project.ex01;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MailController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping(value="mailSending")
	public ModelAndView mailSending(ModelAndView mv, String address) {
		Random random = new Random();
		String number = "";
		
		for(int i=0; i < 5; i++) {
			number += String.valueOf(random.nextInt(10));
		} // for
		
		
		String setfrom = "DnCProductSystem@gmail.com";
		String tomail = address;
		String title = "[D&C] 회원가입 인증 번호 보내드립니다.";
		String content = 
				"<h1>DnCProduct</h1>"
				+ "<h3>아래 인증번호를 입력해주시면 가입 승인이 완료 됩니다.</h3>"
				+ "<p style=\"font-weight:bold; font-size:15px;\">"+ number+ "</p>"
				+ "<hr>"
				+ "DncProduct 에 방문해주셔서 감사합니다.";
		
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8");
			
			messageHelper.setFrom(setfrom);
			messageHelper.setTo(tomail);
			messageHelper.setSubject(title);
			messageHelper.setText(content,true);
			
			mailSender.send(message);
			mv.addObject("result",number);
			
		} catch (Exception e) {
			System.out.println("mailSending Exception => "+e.toString());
			mv.addObject("result",false);
		}
		mv.setViewName("jsonView");
		return mv;
	}// mailsending
} // class