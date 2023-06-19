package com.wish.library.member.domain;

//VO는 주로 Read Only 목적이 강하다. 데이터 자체도 불변하게 설계하는 것이 정석.
//테이블과 관련된 데이터를 VO로 사용.

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class MemberVO {

    private String email; // 회원 아이디
    private String password; //비밀번호
    private Auth role; // 회원 권한 (admin, member)
    private String name;
    private String nickname;
    private Sex mfCode; //성별 (men, female)
    private String cellNo;
    private Date joinDate;
    private Date updateDate;



}
