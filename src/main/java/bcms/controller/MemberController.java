package bcms.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcms.domain.Member;
import bcms.service.AuthService;
import bcms.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {
    
    @Autowired MemberService memberService;
    @Autowired AuthService authService;
    
    @PostMapping("signUp")
    public Object member(Member member) {
        System.out.println("컨트롤러로 넘어옴");
        System.out.println("member"+member);
        HashMap<String,Object> result = new HashMap<>();
        try {
            memberService.add(member);
            result.put("state", "success");
        } catch (Exception e) {
            result.put("state", "fail");
            result.put("message", e.getMessage());
        }
        return result;
    }
    
    @PostMapping("changeUserPassword")
 	public Object changePassword(
 						String existingUserPassword,
 						String newUserPassword,
 						HttpSession session)throws Exception{
 		
     	HashMap<String, Object> resultMap = new HashMap<String, Object>(); 

     	try {
     		
     		Member member = (Member)session.getAttribute("user");
     		member.setPwd(existingUserPassword);
     		System.out.println("들어온 멤버는 ? = "+member);
     		System.out.println(existingUserPassword);
     		System.out.println("비밀번호 확인 후 값은? = "+authService.passwordValidation(member));
     		if(authService.passwordValidation(member)==0)throw new Exception("passwordMatchingFail");
     		
     		member.setPwd(newUserPassword);
     		if(memberService.changeUserPassword(member)==1){
     			resultMap.put("state", "success");
     		}
     		
     	}catch(Exception e) {
     		if(e.getMessage().equals("passwordMatchingFail")) {
     			resultMap.put("state", "passwordMatchingFail");
     		}else {
     		resultMap.put("state", "error");
     		}
     	}
     	
     	return resultMap;
     }
    
    @PostMapping("withdrawal")
    public Object withdrawal(String existingUserPassword, HttpSession session) {
    	
    	try {
    
    	Member member = (Member)session.getAttribute("user");
 		member.setPwd(existingUserPassword);
 		System.out.println("들어온 멤버는 ? = "+member);
 		System.out.println(existingUserPassword);
 		System.out.println("비밀번호 확인 후 값은? = "+authService.passwordValidation(member));
 		
 		if(authService.passwordValidation(member)==0)throw new Exception("passwordMatchingFail");
    	
 		memberService.deleteUserMemberShip(member);
 		
    	}catch(Exception e) {
     		if(e.getMessage().equals("passwordMatchingFail")) {
     			return "passwordMatchingFail";
     		}else {
     			return "error";
     		}
     	}
  			return "success";
    }
    
  
    
}
