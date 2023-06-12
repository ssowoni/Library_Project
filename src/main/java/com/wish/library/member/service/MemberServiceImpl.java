package com.wish.library.member.service;

import com.wish.library.member.domain.MemberVO;
import com.wish.library.member.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberServiceImpl implements MemberService{

    private final MemberMapper mapper;

/*    @Override
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
    }*/

    public MemberVO login(String email, String password){
        log.info("============= member login service");
        MemberVO readMember = mapper.read(email);
        //해당 아이디로 조회된 회원이 있을 때
        if(readMember !=null){
            //회원이 입력한 비밀번호랑 조회한 값의 비밀번호가 같을 때 로그인 성공.
            if(readMember.getPassword().equals(password)){
                return readMember;
            }
        }
        return null;
    }

    @Override
    public MemberVO get(String param) {
        log.info("============= member get service");
        MemberVO readMember = mapper.read(param);
        if(readMember !=null){
            return readMember;
        }
        return null;
    }






    /*@Override
    public MemberVO check(MemberVO member){
        log.info("============= member checkNickname service");
        MemberVO readMember = mapper.duplicationCheck(member);
        if(readMember !=null){
                return readMember;
        }
        return null;
    }*/

    //회원 정보 조회, 회원가입 이메일 중복 검사
/*    @Override
    public MemberVO getEmail(String email){
        log.info("============= member getEmail service");
        MemberVO readMember = mapper.readEmail(email);
        if(readMember !=null){
            return readMember;
        }
        return null;
    }

    //회원가입 시 닉네임 중복 검사
    @Override
    public MemberVO getNickname(String nickname){
        log.info("============= member getEmail service");
        MemberVO readMember = mapper.readNickname(nickname);
        if(readMember !=null){
            return readMember;
        }
        return null;
    }*/


    @Override
    public boolean join(MemberVO member) {

        log.info("============= member join service");
        boolean joinResult = mapper.insert(member)==1;
        return joinResult;
    }

    @Override
    public boolean modify(MemberVO member) {
        log.info("============member modify service");
        boolean updateResult = mapper.update(member) == 1;
        return updateResult;
    }


}
