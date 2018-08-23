package bcms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcms.domain.Member;

@RestController
@RequestMapping("/BusinessCard")
public class BusinessCardController {

	@RequestMapping("list")
	public Object list(HttpSession session) {
		System.out.println("hi");
		
		Member member = (Member)session.getAttribute("user");
		
		System.out.println(member);
		
		return "1";
	}
	
}
