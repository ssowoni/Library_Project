package com.wish.library.member.service;

import com.wish.library.member.domain.MemberVO;

public interface MemberService {

    //public MemberVO login(String email, String pw); //로그인
    public MemberVO login(String email, String password);
    public MemberVO check(MemberVO member);
    public boolean join(MemberVO member);//회원가입

    public boolean modify(MemberVO member);




}
