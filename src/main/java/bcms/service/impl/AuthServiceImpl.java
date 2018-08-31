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
    public Member findPwd(String email) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("email", email);
        return authDao.findPwd(params);
    }
    
    @Override
    public int changePassword(int mno, String changePassword) {
    	HashMap<String, Object>params = new HashMap<>();
    	params.put("mno", mno);
    	params.put("pwd", changePassword);
    	return authDao.changePassword(params);
    }
    
    @Override
    public int checkPassword(Member member) {
    	System.out.println("다오들어옴");
    	return authDao.checkPassword(member);
    }
}
