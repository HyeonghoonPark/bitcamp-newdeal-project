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
    
    @PostMapping("changePassword")
	public Object changePassword(
						String password,
						String changePassword,
						HttpSession session)throws Exception{
		
    	HashMap<String, Object> resultMap = new HashMap<String, Object>(); 

    	try {
    		
    		Member member = (Member)session.getAttribute("user");
    		member.setPwd(password);
    		System.out.println("들어온 멤버는 ? = "+member);
    		System.out.println(changePassword);
    		System.out.println("비밀번호 확인 후 값은? = "+authService.checkPassword(member));
    		if(authService.checkPassword(member)==0)throw new Exception("passwordFail");
    		
    		int mno = member.getMno();
    		if(authService.changePassword(mno, changePassword)==1){
    			resultMap.put("state", "success");
    		}
    		
    	}catch(Exception e) {
    		if(e.getMessage().equals("passwordFail")) {
    			resultMap.put("state", "passwordFail");
    		}else {
    		resultMap.put("state", "error");
    		}
    	}
    	
    	return resultMap;
    }
}
