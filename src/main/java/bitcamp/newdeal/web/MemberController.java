package bitcamp.newdeal.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
    @RequestMapping("/member")
    public void member(Model model) {
        model.addAttribute("name", "박보검");
    }
}
