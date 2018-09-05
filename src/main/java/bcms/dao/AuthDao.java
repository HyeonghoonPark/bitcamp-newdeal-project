package bcms.dao;

import java.util.HashMap;

import bcms.domain.Member;

public interface AuthDao {

	Member findByEmailAndPassword(Member user);
	
	int identifyEmailAddr(String email);

	int passwordValidation(Member member);

	int checkAuthedEmail(Member user);

	int identifyTemailAddr(String email);
	
}
