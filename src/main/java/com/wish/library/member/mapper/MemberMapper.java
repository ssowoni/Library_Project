package com.wish.library.member.mapper;


import com.wish.library.member.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    //회원 정보 조회
    public MemberVO read(String email);

    //회원 가입
    public int insert(MemberVO member);
        
}
