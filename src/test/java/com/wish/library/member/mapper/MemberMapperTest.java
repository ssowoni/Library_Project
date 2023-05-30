package com.wish.library.member.mapper;

import com.wish.library.member.domain.MemberVO;
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
    void mapperTest(){
        //given
        MemberVO member = mapper.read("admin@gmail.com");
        //when,then
        log.info("member={}",member);
        Assertions.assertThat(member.getPassword()).isEqualTo("admin");
    }
}