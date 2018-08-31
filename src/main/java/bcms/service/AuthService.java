package bcms.service;

import bcms.domain.Member;

public interface AuthService {

	Member getMember(Member user);
	
	Member findPwd(String email);

	int changePassword(int mno, String changePassword);

	int checkPassword(Member member);

}
