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
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired AuthService authService; 
	
	@PostMapping("signIn")
	public Object SignIn(Member user, 
							HttpSession session) {
		
		HashMap<String,Object> resultMap = new HashMap<>();

		try {
			
			Member loginUser = authService.getMember(user); 

			if(loginUser == null)throw new Exception("notFindUser");
		
			
			System.out.println("돌아온값은? " +authService.checkAuthedEmail(loginUser));
			
			if(authService.checkAuthedEmail(loginUser)!=1)throw new Exception("notAuthEmail");
					
			//if(AuthedEmailUser != authService.checkAuthedEmail(user))throw new Exception("notAuthEmail");
			
			session.setAttribute("user", loginUser);
			resultMap.put("state","success");
		
		}catch(Exception e) {
			if(e.getMessage()=="notFindUser") {
				resultMap.put("state", "notFind");
			}else if(e.getMessage()=="notAuthEmail"){
				resultMap.put("state", "notAuthEmail");
			}else{
				resultMap.put("state", "error");
				resultMap.put("message", e.getMessage());
			}
		}
		
		return resultMap;
	}
	
	
	@RequestMapping("/identifyEmailAddr")
	public Object chkEmail(String email, Model model) {

		HashMap<String, Object> resultMap = new HashMap<>();
		System.out.println(email);
		int chkEmail = authService.identifyEmailAddr(email);
		System.out.println(chkEmail);
		if(chkEmail == 0) {
			resultMap.put("state", "success");
		}else {
			resultMap.put("state", "fail");
		}
		
		return resultMap;
		
	}
	
    @PostMapping("forgotPwd")
    public Object forgotPwd(String email) {
        
        HashMap<String, Object> result = new HashMap<>();
        
        try {
            Member findPwd = authService.findPwd(email);
            
            if(findPwd == null) 
                throw new Exception("입력하신 이메일과 일치하는 회원이 없습니다.");
            
        }catch(Exception e){
            
        }
        
        return result;
    }
    
}
