package bcms.dao;

import java.util.HashMap;

import bcms.domain.Member;

public interface MemberDao {

	int insert(Member user);
	
	int changeUserPassword(Member member);

	int deleteUserMemberShip(Member member);
	
}
