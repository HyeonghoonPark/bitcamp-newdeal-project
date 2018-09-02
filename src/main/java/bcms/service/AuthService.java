package bcms.service;

import bcms.domain.Member;

public interface AuthService {

	Member getMember(Member user);
	
	Member findPwd(String email);

	int passwordValidation(Member member);

}
