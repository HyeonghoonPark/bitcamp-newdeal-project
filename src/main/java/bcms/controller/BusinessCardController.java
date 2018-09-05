package bcms.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import bcms.domain.BusinessCard;
import bcms.domain.Member;
import bcms.service.BusinessCardService;

@RestController
@RequestMapping("/businesscard")
public class BusinessCardController {

	@Autowired BusinessCardService businessCardService;
	
	@Autowired ServletContext sc;
	
	@RequestMapping("list")
	public Object list(HttpSession session)throws Exception {
		
		
		HashMap<String, Object> resultMap = new HashMap<>();

		HashMap<String, Object> returnMap = new HashMap<>();
		
		try {
			
		System.out.println("들어옴");
		
		// 세선에서 유저 정보 받아오기
		Member member = (Member)session.getAttribute("user");
		
		// 멤버 정보 출력
		System.out.println(member);
		
		// 카드 정보 추출 (NAME 내림차순)
		List<BusinessCard> cardList = businessCardService.getBusinessCardList(member.getMno());
		
		// return할 Map에 멤버 정보 담기
		resultMap.put("member", member);
		
		// 처음 사용한 유저이면 Exception으로 바로 메시지 넘기기
		if(cardList.size() == 0)throw new Exception("firstUse");
		
		// 멤버 리스트 정렬하기 위한 초성 출력, Map[]을 getInitial 안에서 사용하기 위해 count로 조건을 건다.
		resultMap.put("count", 0);
		
		System.out.println("증가하기전 count = " +resultMap.get("count"));
		// 처음 들어가서 Map[]과 초성들을 출력, 초성 정렬을 한다.
		getInitial(cardList, resultMap);
		
		// 증가된 카운터를 확인
		System.out.println("증가한 후 count = " +resultMap.get("count"));
		
		// 다시 들어가서 HashMap에 재배치
		getInitial(cardList, resultMap);
		
		System.out.println("담고 나온 maps = "+ resultMap.get("maps"));
		
		// 쌓여있는 resultMap을 확인
		System.out.println("resultMap = "+resultMap);
		
		// 다 확인 됐으면 상태값을 넘겨줌.
		resultMap.put("state", "success");
		
		}catch(Exception e) {
			// 처음 사용하는 유저이면 처음 사용자임을 알려주는 상태값을 넘겨줌
			if(e.getMessage()=="firstUse") {
				resultMap.put("state", "first");
			}else {
			// try문 도중 에러가 발생하면 에러 메시지를 상태값으로 넘겨줌
			resultMap.put("state", e);
			}
		}
		
		// 에러가 발생하지 않았으면 리턴값을 넘겨줌.
		return resultMap;
	}
	
	// 초성 추출
	@SuppressWarnings("unchecked")
	public static Object getInitial(List<BusinessCard> cardList, HashMap<String, Object> resultMap){

		// 함수 처음 들어오면 기본 셋팅을 깔고 두번째 들어오면 데이터를 대입한다.
		int functionCount = (int) resultMap.get("count");
		// 해당 초음이 몇개 있는지
		int choCount = 0;
		
		// 카드 리스트 이름 중 첫글자만 담기 위해 List를 사용
		List names = new ArrayList();
		
		// 초성들별로 카드 이름을 나누기 위해 Map[]안에 Map[]을 사용함.
		HashMap[] maps;
		
		// 초성들을 중복제거하고 순차적으로 나열하기 위한 List
		List chonames = new ArrayList();
		
		if(functionCount == 1) {
		chonames = (List)resultMap.get("chonames");
		}
		
		// 전체 HashMap 비교 생성
		maps = new HashMap[chonames.size()];
		
		for(int i = 0; i < chonames.size(); i++) {
			maps[i] = new HashMap();
		}
		
		
		// 반복문을 돌려 첫글자만 names list에 담음
		for(int i = 0; i < cardList.size(); i++) {
			names.add(cardList.get(i).getName().substring(0, 1));
		}
				
		
		for (int i = 0; i < names.size(); i++) {
			
			String fullStr = (String) names.get(i);
			
			char comVal = (char) (fullStr.charAt(0)-0xAC00);
			
		
			if (comVal >= 0 && comVal <= 11172){

				// 한글일경우 

					// 초성만 입력 했을 시엔 초성은 무시해서 List에 추가합니다.

					char uniVal = (char)comVal;



					// 유니코드 표에 맞추어 초성 중성 종성을 분리합니다..

					char cho = (char) ((((uniVal - (uniVal % 28)) / 28) / 21) + 0x1100);

					if(cho!=4519){
						
						if(functionCount==0) {
							
							chonames.add(String.valueOf(cho));
						}else {
							System.out.println("비교 리스트는 ? =" +resultMap.get("chonames"));
							System.out.println("들어온 한글은 = " +cardList.get(i).getName());
							System.out.println("그 한글이 몇번 째 자리에 있는지? = " + chonames.indexOf(String.valueOf(cho)));
							/*maps[chonames.indexOf(String.valueOf(cho))].put(cho, cardList.get(i));*/
							maps[chonames.indexOf(String.valueOf(cho))].put(choCount,cardList.get(i));
							choCount++;
						}
						
					}

			} else {

				// 한글이 아닐경우

				comVal = (char) (comVal+0xAC00);
				
				if(functionCount==0) {
					chonames.add(String.valueOf(comVal));
				}else {
					System.out.println("비교 리스트는 ? =" +resultMap.get("chonames"));
					System.out.println("들어온 영어은 = " +cardList.get(i).getName());
					System.out.println("그 영어가 몇번 째 자리에 있는지? = " + chonames.indexOf(String.valueOf(comVal)));
					maps[chonames.indexOf(String.valueOf(comVal))].put(choCount,cardList.get(i));	
					choCount++;
					/*maps[chonames.indexOf(String.valueOf(comVal))].put(i,"0");*/
				}
				
			}
		}

		if(functionCount == 0) {
		// LinkedHashSet으로 초성 중복 제거
		LinkedHashSet removeOverLap = new LinkedHashSet(chonames); 
		
		chonames.clear();
		
		// 초성 제거한 것 다시 list로 변환 
		// for문 돌때마다 일치하는 초성을 특정 HashMap에 저장하기 위한 비교 list - indexof 사용
		chonames.addAll(removeOverLap); 
		
		
		resultMap.put("chonames", chonames);
		

		resultMap.put("count", 1);
		
		}else {
		
			for(int i = 0 ; i < maps.length; i++) {
				System.out.println("map의"+i+"번째는? = " +maps[i]);
			}
			
		}
		
		resultMap.put("maps",maps);
		
		return resultMap;
	}
	
	@RequestMapping("/getCardInfo/{cardNo}")
	public Object getCardInfo(
							@PathVariable("cardNo") int cardNo,
							HttpSession session)throws Exception{
		
		HashMap<String,Object> resultMap = new HashMap<>();
		
		try {
		Member member = (Member)session.getAttribute("user");
	    
		int mno = member.getMno();
		
		BusinessCard selectoneCard =  businessCardService.getSingleBusinessCardInfo(mno, cardNo);
		
		resultMap.put("card", selectoneCard);
		
		resultMap.put("state", "success");
		
		}catch(Exception e) {
			
			resultMap.put("state", "error");
			
		}
		
		return resultMap;
	}
	
	@RequestMapping("/addBziCard")
	 public Object addBziCard(
	            BusinessCard businessCard,
	            MultipartFile[] files,
	            HttpSession session) {
	        
	        System.out.println("upload01()...호출됨!");
	        
	        System.out.println(businessCard);
	        
	        Member member = (Member)session.getAttribute("user");
	        
	        HashMap<String,Object> resultMap = new HashMap<>();
	        
	        ArrayList<String> filenames = new ArrayList<>();
	        
	        try {
	        	if(member==null)throw new Exception("login");
	            
	        	for (MultipartFile file : files) {
	                if (file.isEmpty()) continue;
	                
	                String newfilename = UUID.randomUUID().toString(); 
	                String path = sc.getRealPath("/files/" + newfilename);
	                System.out.println(path);
	                
	                businessCard.setMno(member.getMno());
	                businessCard.setImg(newfilename);
	                
	                int returnInt = businessCardService.addBusinessCard(businessCard);
	                
	                System.out.println("들어갓나?" + returnInt);
	                
	                file.transferTo(new File(path));
	            }
	        } catch (Exception e) {
	        	if(e.getMessage()=="login") {
	        		resultMap.put("state", "session");
	        		return resultMap;
	        	}else {        		
	        		e.printStackTrace();
	        	}
	        }
	        
	        return "성공했습니다!";
	    }
	
	@RequestMapping("/deleteBziCard")
	public Object deleteBziCard()throws Exception{
		
		System.out.println("컨트롤러 들어옴");
		
		return 0;
	}
	
}
