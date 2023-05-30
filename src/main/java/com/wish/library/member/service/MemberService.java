package com.wish.library.member.service;

import com.wish.library.member.domain.MemberVO;

public interface MemberService {

    public MemberVO login(String email, String pw); //로그인

    public void join(MemberVO member);//회원가입



}
