package bcms.dao;

import bcms.domain.Member;

public interface AuthDao {

	Member findByEmailAndPassword(Member user);
	
}
