package com.wish.library.member.mapper;

import com.wish.library.member.domain.Auth;
import com.wish.library.member.domain.MemberVO;
import com.wish.library.member.domain.Sex;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

// 실 데이터베이스에 연결 시 필요한 어노테이션
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@MybatisTest
@Slf4j
class MemberMapperTest {

    @Autowired
    private  MemberMapper mapper;

    @Test
    void mapperConnectTest(){
        log.info("mapper={}",mapper.toString());
    }
    @Test
    @DisplayName("mapper 연결 확인")
    void mapperIdTest(){
        //given
        MemberVO member = mapper.read("admin@gmail.com");
        //when,then
        log.info("member={}",member);
        Assertions.assertThat(member.getPassword()).isEqualTo("admin");
    }

    @Test
    void mapperNicknameTest(){
        //given
        MemberVO member = mapper.read("admin@gmail.com");
        //when,then
        log.info("member={}",member);
    }

    @Test
    @DisplayName("회원 정보 삽입")
    void insertMemberTest(){
        MemberVO member = new MemberVO();
        member.setEmail("sw@gmail.com");
        member.setPassword("member");
        member.setName("테스트회원");
        member.setNickname("회원1");
        member.setMfCode(Sex.F);
        member.setCellNo("010-1111-2222");
        log.info("========== member={}", member);

/*
        MemberVO member = new MemberVO("sw@gmail.com", "member", null, "회원1"
            , "회원1", Sex.F, "010-8888-7777",null,null);*/



        mapper.insert(member);

        log.info(mapper.read("sw@gmail.com").toString());

    }


















}