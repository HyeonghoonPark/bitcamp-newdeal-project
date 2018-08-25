package bcms.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
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

		try {
			
		System.out.println("들어옴");
			
		Member member = (Member)session.getAttribute("user");
		
		System.out.println(member);
		
		List<BusinessCard> cardList = businessCardService.list(Integer.parseInt(member.getMno()));
		
		System.out.println("맨처음 카드리스트 = " +cardList);
		// 초성 추출
		HashMap<String, Object> buziCardList = getInitial(cardList);
		
		resultMap.put("firstName", buziCardList.get("cho"));
		
		buziCardList.remove("cho");
		resultMap.put("choMap", buziCardList);
		
		resultMap.put("member", member);
		
		resultMap.put("state", "success");
		
		}catch(Exception e) {
			resultMap.put("state", e);
		}
		
		return resultMap;
	}
	
	// 초성 추출
	@SuppressWarnings("unchecked")
	public static HashMap<String, Object> getInitial(List<BusinessCard> cardList){

		List names = new ArrayList();
		
		ArrayList list = new ArrayList();
		
		HashMap[] maps;
		
		maps = new HashMap[40];
		
		for(int i = 0; i < 40; i++) {
			maps[i] = new HashMap();
		
			/*for(int j = 0; j < 40; j++) {
				maps[i].put(j, new HashMap());
			}*/
		}
		
		
		HashMap<Integer,String> listMap = new HashMap<>();
		
		HashMap<String, Object> resultMap = new HashMap<>();
		
		int count = 0;
		int hangulCount = 0;
		int englishCount = 0;
		String state = "";
		char compareHan = 0;
		char compareEng = 0;
		int moveCheck = 0;
		
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
								moveCheck = 0;
							}else{
								moveCheck++;
							}
							
							resultMap.put(String.valueOf(cho), null);
							
							System.out.println(count);
							
							// 할 것 : for문 안에서 초음 매칭해서 map배열 갯수만큼 생성하기!
							// 방법! 키값을 번호로 지정하고 그냥 cardList를 싣기
							
							HashMap test = new HashMap();
							
							test.put("name", cardList.get(j).getBcno());
							
							//maps[count].put(cardList.get(j).getBcno(), cardList.get(j).getName());
							maps[count].put(moveCheck, cardList.get(j));
							
							System.out.println("들어갔는지1 확인 hashMap[]" +maps[count]);
							
							resultMap.put(String.valueOf(cho), maps[count]);
							
							list.add(compareHan);
							
							state = "한글";
							
						}else {
							
							++count;
							moveCheck = 0;
							
							compareHan = cho;
							
							resultMap.put(String.valueOf(cho), null);
							
							System.out.println(count);
							
							//maps[count].put(cardList.get(j).getBcno(), cardList.get(j).getName());
							//maps[count].put("name", cardList.get(j).getBcno());
							//maps[count].put("bcno", cardList.get(j).getName());
							maps[count].put(moveCheck, cardList.get(j));
							
							System.out.println("들어갔는지2 확인 hashMap[]" +maps[count]);
							
							resultMap.put(String.valueOf(cho), maps[count]);
							
							list.add(compareHan);
							
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
						moveCheck = 0;
					}else {
						moveCheck ++;
					}
					
					resultMap.put(String.valueOf(comVal), null);
					
					System.out.println(count);
					
					//maps[count].put(cardList.get(j).getBcno(), cardList.get(j).getName());
					//maps[count].put("name", cardList.get(j).getBcno());
					//maps[count].put("bcno", cardList.get(j).getName());
					maps[count].put(moveCheck, cardList.get(j));
					
					System.out.println("들어갔는지 확인1 hashMap[]" +maps[count]);
					
					resultMap.put(String.valueOf(comVal), maps[count]);
					
					list.add(compareEng);
					
					state = "영어";
					
					
				}else {
					
					
					++count;
					moveCheck = 0;
					
					compareEng = comVal;

					resultMap.put(String.valueOf(comVal), null);
					
					System.out.println(count);

					
					//maps[count].put(cardList.get(j).getBcno(), cardList.get(j).getName());
					//maps[count].put("name", cardList.get(j).getBcno());
					//maps[count].put("bcno", cardList.get(j).getName());
					maps[count].put(moveCheck, cardList.get(j));
					
					System.out.println("들어갔는지 확인2 hashMap[]" +maps[count]);
					
					resultMap.put(String.valueOf(comVal), maps[count]);
					
					list.add(compareEng);
					
					state = "영어";
					
				}				
				
			}
			
		}
		
		
		LinkedHashSet removeOverLap = new LinkedHashSet();
		
		
		System.out.println("링크드리스트 들어가기 전 = "+list);
		
		Collections.sort(list);

		System.out.println("컬렉션 들어간 후 = "+list);

		removeOverLap.addAll(list);
		
		System.out.println("링크드리스트 들어갔을 때 = "+removeOverLap);
		
		list.clear();
		list.add(removeOverLap);
		
		System.out.println("링크드리스트 들어갔을 때 = "+removeOverLap);
		
		resultMap.put("cho", list);
		
		System.out.println("result 목록은 =" +resultMap);
		
		return resultMap;
		
	}
	
}
