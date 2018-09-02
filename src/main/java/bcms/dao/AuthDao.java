package bcms.dao;

import java.util.HashMap;

import bcms.domain.Member;

public interface AuthDao {

	Member findByEmailAndPassword(Member user);
	
	int identifyEmailAddr(String email);

	Member findPwd(HashMap<String, Object> params);

	int passwordValidation(Member member);
	
}
