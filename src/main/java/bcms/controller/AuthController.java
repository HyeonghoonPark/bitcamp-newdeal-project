package bcms.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bcms.domain.Member;
import bcms.service.AuthService;

@RestController
@RequestMapping("/Auth")
public class AuthController {
	
	@Autowired AuthService authService; 
	
	@PostMapping("SignIn")
	public Object SignIn(Member user, HttpSession session) {
		
		HashMap<String,Object> resultMap = new HashMap<>();

		
		try {
			Member loginUser = authService.getMember(user); 

			if(loginUser == null)throw new Exception("에러가 발생했습니다!");
		
			session.setAttribute("user", loginUser);
			resultMap.put("state","success");
		
		}catch(Exception e) {
			resultMap.put("state", "error");
			resultMap.put("message", e.getMessage());
		}
		
		
		return resultMap;
	}
	
}
