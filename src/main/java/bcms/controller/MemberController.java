package bcms.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcms.domain.Member;
import bcms.service.MemberService;

@RestController
@RequestMapping("/Member")
public class MemberController {

	@Autowired MemberService memberService; 
	
	@PostMapping("SignUp")	
	public Object signUp(Member user, Model model) {
		
		HashMap<String, Object> resultMap = new HashMap<>();
		
		int chkEmail = memberService.identify(user.getEmail());
		
		System.out.println(chkEmail);
		
		if(chkEmail == 0) {
			
			int cnt = memberService.add(user);
			
			if(cnt == 1) {
			
				resultMap.put("state", "success");
				
			}
			
		}else {
			
			resultMap.put("state", "fail");
			
		}
		
		return resultMap;
	
	}
	
	@RequestMapping("/chkEmail")
	public Object chkEmail(String email, Model model) {

		HashMap<String, Object> resultMap = new HashMap<>();
		System.out.println(email);
		int chkEmail = memberService.identify(email);
		System.out.println(chkEmail);
		if(chkEmail == 0) {
			resultMap.put("state", "success");
		}else {
			resultMap.put("state", "fail");
		}
		
		return resultMap;
		
	}
}
