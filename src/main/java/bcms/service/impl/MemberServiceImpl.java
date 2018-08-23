package bcms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcms.dao.MemberDao;
import bcms.domain.Member;
import bcms.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired MemberDao memberDao;
	
	@Override
	public int add(Member user) {
		return memberDao.insert(user);
	}
	
	@Override
	public int identify(String email) {
		return memberDao.identifyEamil(email);
	}
	
}
