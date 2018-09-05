package bcms.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcms.dao.AuthDao;
import bcms.domain.Member;
import bcms.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired AuthDao authDao;
		
	@Override
	public Member getMember(Member user) {
		return authDao.findByEmailAndPassword(user);
	}
	
	@Override
	public int identifyEmailAddr(String email, String checkPage) {
		System.out.println("이메일은 = "+ email);
		System.out.println("확인 페이지는 = "+ checkPage);
		
		int count;
		
		count = authDao.identifyEmailAddr(email);
		
		if(checkPage!=null) { // 비밀번호 찾기 시 사용하는 서비스 
		 
			// 2가 되면 이미 비밀번호 찾기 이메일이 날라간 계정임
			if(authDao.identifyTemailAddr(email)==1) {
				count = 2;
			}
			
		}
		
		return count;
		
	}
	
    @Override
    public int passwordValidation(Member member) {
    	System.out.println("다오들어옴");
    	return authDao.passwordValidation(member);
    }

	@Override
	public int checkAuthedEmail(Member user) {
		return authDao.checkAuthedEmail(user);
	}
}
