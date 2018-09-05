package bcms.service;

import bcms.domain.Member;

public interface AuthService {

	Member getMember(Member user);
	
	int passwordValidation(Member member);

	int checkAuthedEmail(Member user);

	int identifyEmailAddr(String email, String checkPage);

}
