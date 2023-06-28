package com.wish.library.member.mapper;


import com.wish.library.member.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    //회원 정보 조회
    public MemberVO read(String param);

    public MemberVO readByEmail(String email);
    //닉네임으로 회원정보 조회
   /* MemberVO readNickname(String nickname);*/

    //회원 가입
    public int insert(MemberVO member);

    //회원 수정
    public int update(MemberVO member);
        
}
