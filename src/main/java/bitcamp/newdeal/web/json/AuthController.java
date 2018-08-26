package bitcamp.newdeal.web.json;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bitcamp.newdeal.domain.Member;
import bitcamp.newdeal.service.MemberService;

@RestController
@RequestMapping("/member")
public class AuthController {
    
    @Autowired MemberService memberService;

    @PostMapping("forgotPwd")
    public Object forgotPwd(String email) {
        
        HashMap<String, Object> result = new HashMap<>();
        
        try {
            Member findPwd = memberService.findPwd(email);
            
            if(findPwd == null) 
                throw new Exception("입력하신 이메일과 일치하는 회원이 없습니다.");
            
        }catch(Exception e){
            
        }
        
        return result;
    }
}
