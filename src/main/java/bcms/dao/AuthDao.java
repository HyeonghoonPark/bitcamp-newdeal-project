package bcms.dao;

import java.util.HashMap;

import bcms.domain.Member;

public interface AuthDao {

	Member findByEmailAndPassword(Member user);
	
	Member findPwd(HashMap<String, Object> params);

	int changePassword(HashMap<String, Object> params);

	int checkPassword(Member member);
	
}
