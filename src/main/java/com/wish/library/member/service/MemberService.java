package com.wish.library.member.service;

import com.wish.library.member.domain.MemberVO;

public interface MemberService {

    //public MemberVO login(String email, String pw); //로그인
    //public MemberVO login(String email, String password);

    MemberVO getMemberByEmail(String email);
    public MemberVO get(String param);

/*    public MemberVO getNickname(String nickname);*/
    public boolean join(MemberVO member);//회원가입

    public boolean modify(MemberVO member);




}
