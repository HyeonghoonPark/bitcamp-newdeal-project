package bcms.dao;

import java.util.HashMap;

import bcms.domain.Member;

public interface MemberDao {

	int insert(Member user);
	
	int identifyEamil(String email);

	int changeUserPassword(Member member);

	int deleteUserMemberShip(Member member);
	
}
