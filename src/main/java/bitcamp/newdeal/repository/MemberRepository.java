package bitcamp.newdeal.repository;

import java.util.HashMap;
import java.util.Map;

import bitcamp.newdeal.domain.Member;

public interface MemberRepository {

    int insert(Member member);
    Member findByEmailAndPassword(Map<String,Object> params);
    Member findPwd(HashMap<String, Object> params);

}
