package bcms.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

		try {
			
		System.out.println("들어옴");
			
		Member member = (Member)session.getAttribute("user");
		
		System.out.println(member);
		
		List<BusinessCard> cardList = businessCardService.list(Integer.parseInt(member.getMno()));
		
		System.out.println("맨처음 카드리스트 = " +cardList);
		// 초성 추출
		HashMap<String, Object> choMap = getInitial(cardList);
		
		
		
		//choSettings(choMap, cardList);
		
		
		resultMap.put("choMap", choMap);
		
		resultMap.put("cardList", cardList);
		
		resultMap.put("member", member);
		
		}catch(Exception e) {
			resultMap.put("error", e);
		}
		
		return resultMap;
	}
	
	public void choSettings(LinkedHashSet<String> choMap, List<BusinessCard> cardList) {
		
		System.out.println("초맵 초성 ="+choMap);
		System.out.println("초맵 리스트 ="+cardList);
		
		System.out.println(cardList.size());
		
		/*List duplicateRemoveList1 = (List)choMap.get("duplicateRemoveList");
		List<BusinessCard> list1 = (List<BusinessCard>) choMap.get("cardList");
		*/
		
		System.out.println(cardList.get(0).getName().substring(0, 1));
		
	
		
	}

	// 초성 추출
	public static HashMap<String, Object> getInitial(List<BusinessCard> cardList){

		List names = new ArrayList();
		
		List list = new ArrayList();
		
		HashMap[] maps;
		
		maps = new HashMap[100];
		
		for(int i = 0; i < 50; i++) {
			maps[i] = new HashMap();
		}
		
		HashMap<Integer,String> listMap = new HashMap<>();
		
		HashMap<String, Object> resultMap = new HashMap<>();
		
		int count = 0;
		int hangulCount = 0;
		int englishCount = 0;
		String state = "";
		char compareHan = 0;
		char compareEng = 0;
		
		for(int i = 0; i < cardList.size(); i++) {
			names.add(cardList.get(i).getName().substring(0, 1));
		}
		
		System.out.println("첫글자만 짜른 리스트" + names);
		
		for(int j = 0; j < names.size(); j++) {
			
			System.out.println("카운터세는곳 = "+count);
			
			String fullStr = (String) names.get(j);

			char comVal = (char) (fullStr.charAt(0)-0xAC00);

			if (comVal >= 0 && comVal <= 11172){
				
				// 한글일경우 

					// 초성만 입력 했을 시엔 초성은 무시해서 List에 추가합니다.
					char uniVal = (char)comVal;

					// 유니코드 표에 맞추어 초성 중성 종성을 분리합니다..
					char cho = (char) ((((uniVal - (uniVal % 28)) / 28) / 21) + 0x1100);

					if(hangulCount==0) {
						compareHan = cho;
						System.out.println("한글 생성자 compareHan" + compareHan);
						hangulCount++;
					}
					
					if(cho!=4519){
						
						System.out.println("count는 "+count+" compareHan은" + compareHan);
						System.out.println("count는 "+count+" cho는" + cho);

						if(compareHan == cho){
							
							if(state != "한글") {
								++count;
							}
							
							resultMap.put(String.valueOf(cho), null);
							
							System.out.println(count);
							
							maps[count].put(cardList.get(j).getBcno(), cardList.get(j).getName());
							
							System.out.println("들어갔는지1 확인 hashMap[]" +maps[count]);
							
							resultMap.put(String.valueOf(cho), maps[count]);
							
							list.add(cardList.get(j).getName());
							
							state = "한글";
							
						}else {
							
							++count;
							
							compareHan = cho;
							
							resultMap.put(String.valueOf(cho), null);
							
							System.out.println(count);
							
							maps[count].put(cardList.get(j).getBcno(), cardList.get(j).getName());
							
							System.out.println("들어갔는지2 확인 hashMap[]" +maps[count]);
							
							resultMap.put(String.valueOf(cho), maps[count]);
							
							list.add(cardList.get(j).getName());
							
							state = "한글";
								
						}

						
					}

			} else {

				// 한글이 아닐경우

				comVal = (char) (comVal+0xAC00);
				
				if(englishCount == 0) {
					compareEng = comVal;
					System.out.println("영어 생성자 들어옴 = "+ compareEng);
					englishCount++;
				}
				
				if(compareEng == comVal) {
					
					if(state != "영어") {
						++count;
					}
					
					resultMap.put(String.valueOf(comVal), null);
					
					System.out.println(count);
					
					maps[count].put(cardList.get(j).getBcno(), cardList.get(j).getName());
					
					System.out.println("들어갔는지 확인1 hashMap[]" +maps[count]);
					
					resultMap.put(String.valueOf(comVal), maps[count]);
					
					list.add(cardList.get(j).getName());
					
					state = "영어";
					
				}else {
					
					
					++count;
					
					compareEng = comVal;

					resultMap.put(String.valueOf(comVal), null);
					
					System.out.println(count);

					
					maps[count].put(cardList.get(j).getBcno(), cardList.get(j).getName());
					
					System.out.println("들어갔는지 확인2 hashMap[]" +maps[count]);
					
					resultMap.put(String.valueOf(comVal), maps[count]);
					
					list.add(cardList.get(j).getName());
					
					state = "영어";
					
				}				
				
			}
			
		}
		
		LinkedHashSet<String> duplicateRemoveList = new LinkedHashSet<String>(list);
		
		/*System.out.println("링크드해쉬셋 = " + duplicateRemoveList);
		
		resultMap.put("duplicateRemoveList", duplicateRemoveList);
*/
		System.out.println("중복제거  = "+duplicateRemoveList);
	/*	Iterator it = duplicateRemoveList.iterator();
		while(it.hasNext()) {
			String str = String.valueOf(it.next());
			System.out.println(str);
			resultMap.put(str,null);
		}*/
			
		System.out.println("duplicateRemoveList = " +duplicateRemoveList);
		
		System.out.println("result 목록은 =" +resultMap);
		
		return resultMap;
		
	}
	
}
