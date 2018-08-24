package bitcamp.newdeal.service;

import bitcamp.newdeal.domain.Member;

public interface MemberService {

    int add(Member member);

    Member getMember(String name, String email, String password);
    
    Member findPwd(String email);

}
