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
    public void changePassword(Member member) {

    	authDao.changePassword(member);
    }
}
