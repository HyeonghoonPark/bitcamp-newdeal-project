package bcms.service;

import bcms.domain.Member;

public interface MemberService {

	int add(Member user);
	
	int changeUserPassword(Member member);
	
	int deleteUserMemberShip(Member member);

}
