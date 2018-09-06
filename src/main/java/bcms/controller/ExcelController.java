package bcms.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import bcms.domain.BusinessCard;
import bcms.domain.Member;
import bcms.service.BusinessCardService;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired BusinessCardService businessCardService;
    
    
    @RequestMapping("download")
    public ModelAndView excelTransform(Map<String, Object> ModelMap, HttpSession session) throws Exception {
        List<BusinessCard> excelList = null;
        
        Member member = (Member)session.getAttribute("user");
        
        excelList = businessCardService.getBusinessCardList(member.getMno());
        System.out.println("excelList : "+excelList );
        
        ModelMap.put("excelList", excelList);
        System.out.println("download");
        ModelAndView modelAndView = new ModelAndView("excelView", "excelList", excelList);
        return modelAndView;
    }

}