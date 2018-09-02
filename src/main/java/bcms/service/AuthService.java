package bcms.service;

import bcms.domain.Member;

public interface AuthService {

	Member getMember(Member user);
	
	int identifyEmailAddr(String email);
	
	Member findPwd(String email);

	int passwordValidation(Member member);

}
