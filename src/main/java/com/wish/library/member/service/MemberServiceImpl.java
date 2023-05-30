package com.wish.library.member.service;

import com.wish.library.member.domain.MemberVO;
import com.wish.library.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberMapper mapper;

    @Override
    public MemberVO login(String email,String pw) {
        log.info("============= member login service");
        MemberVO member = mapper.read(email);
        //해당 아이디로 조회된 회원이 있을 때
        if(member !=null){
            //회원이 입력한 비밀번호랑 조회한 값의 비밀번호가 같을 때 로그인 성공.
            if(pw.equals(member.getPassword())){
                return member;
            }
        }
        return null;
    }

    @Override
    public void join(MemberVO member) {

        log.info("============= member join service");
        mapper.insert(member);
    }


}
