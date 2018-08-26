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
@RequestMapping("/member")
public class MemberController {
    
    @Autowired MemberService memberService;
    
    @PostMapping("signup")
    public Object member(Member member) {
        System.out.println("컨트롤러로 넘어옴");
        System.out.println("member"+member);
        HashMap<String,Object> result = new HashMap<>();
        try {
            memberService.add(member);
            result.put("status", "success");
        } catch (Exception e) {
            result.put("status", "fail");
            result.put("message", e.getMessage());
        }
        return result;
    }
    
  
    
}
