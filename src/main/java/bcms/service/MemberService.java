package bcms.service;

import bcms.domain.Member;

public interface MemberService {

	int add(Member user);
	
	int identify(String email);
	
	int changeUserPassword(Member member);
	
	int deleteUserMemberShip(Member member);

}
