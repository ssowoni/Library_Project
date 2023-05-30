package com.wish.library.member.comtroller;

import com.wish.library.member.domain.MemberVO;
import com.wish.library.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @GetMapping("/login")
    public String loginForm(){
        return "/member/login";
    }

    @PostMapping("/login")
    //pw는 필수값이 아니다. ( 회원가입할 때 id 중복 체크도 하기 위해서)
    public String login(@RequestParam("email") String email, @RequestParam(value = "password", required = false) String pw
                        , HttpSession session){

        MemberVO loginMember = service.login(email, pw);
        log.info("==========loginMember={}", loginMember);
        if(loginMember == null){
            return "redirect:/login";
        }
        session.setAttribute("nickname", loginMember.getNickname());
        //redirect는 경로로 설정
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        log.info("로그아웃");
        return "redirect:/";
    }


    @GetMapping("/join")
    public String joinForm(){
        return "/member/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute MemberVO member){

        log.info("==========joinMember={}", member);
        service.join(member);
        return "redirect:/login";
    }




}
