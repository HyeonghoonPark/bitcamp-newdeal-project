package bcms.service.impl;

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
}
