package bcms.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bcms.domain.BusinessCard;
import bcms.domain.Member;
import bcms.service.BusinessCardService;

@RestController
@RequestMapping("/BusinessCard")
public class BusinessCardController {

	@Autowired BusinessCardService businessCardService;
	
	@RequestMapping("list")
	public Object list(HttpSession session)throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<>();

		HashMap<String, Object> choMap = new HashMap<>();
		

		List names = new ArrayList();
		
		try {
			
		System.out.println("들어옴");
			
		Member member = (Member)session.getAttribute("user");
		
		System.out.println(member);
		
		List<BusinessCard> list = businessCardService.list(Integer.parseInt(member.getMno()));
		
		for(int i = 0; i < list.size(); i++) {
			names.add(list.get(i).getName().substring(0, 1));
		}
		
		resultMap.put("cho", getInitial(names));
		
		resultMap.put("member", member);
		
		System.out.println(member);

		}catch(Exception e) {
			resultMap.put("error", e);
		}
		
		return resultMap;
	}
	
	public static HashMap<String, Object> getInitial(List<String> names){

		

		String resultStr="";

		List list = new ArrayList();
		
		HashMap<String, Object> choList = new HashMap<>();
		
		for(int j = 0; j < names.size(); j++) {
			
			String fullStr = names.get(j);
		

		for (int i = 0; i < fullStr.length(); i++) {

			char comVal = (char) (fullStr.charAt(i)-0xAC00);



			if (comVal >= 0 && comVal <= 11172){

				// 한글일경우 

				

					// 초성만 입력 했을 시엔 초성은 무시해서 List에 추가합니다.

					char uniVal = (char)comVal;



					// 유니코드 표에 맞추어 초성 중성 종성을 분리합니다..

					char cho = (char) ((((uniVal - (uniVal % 28)) / 28) / 21) + 0x1100);

					char jung = (char) ((((uniVal - (uniVal % 28)) / 28) % 21) + 0x1161);

					char jong = (char) ((uniVal % 28) + 0x11a7);



					if(cho!=4519){

						list.add(cho);
						
						resultStr = resultStr + cho;

					}

					if(jung!=4519){

						//System.out.print(jung+" ");

					}

					if(jong!=4519){

						//System.out.print(jong+" ");

					}



			} else {

				// 한글이 아닐경우

				comVal = (char) (comVal+0xAC00);
				
				resultStr =resultStr + comVal;				

			}

		}

		} // 리스트 포문 
/*		String key = Character.toString(cho);
		choList.put(key, key);
*/		

		
		for(int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));			
		}
		
		return choList;

	}
	
}
