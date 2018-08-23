package bcms.dao;

import bcms.domain.Member;

public interface MemberDao {

	int insert(Member user);
	
	int identifyEamil(String email);

}
