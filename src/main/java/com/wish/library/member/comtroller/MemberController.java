package com.wish.library.member.comtroller;

import com.wish.library.member.domain.MemberVO;
import com.wish.library.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    /*@PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam(value = "password") String pw
                        , HttpSession session){

        MemberVO loginMember = service.login(email, pw);
        log.info("==========loginMember={}", loginMember);
        if(loginMember == null){
            return "redirect:/login";
        }
        session.setAttribute("nickname", loginMember.getNickname());
        //redirect는 경로로 설정
        return "redirect:/";
    }*/

     @PostMapping("/login")
    public String login(@RequestParam("email") String email, String password , HttpSession session){

        MemberVO loginMember = service.login(email, password);
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
    public String join(@ModelAttribute MemberVO member,  RedirectAttributes rttr){

        log.info("==========joinMember={}", member);
        if( service.join(member)){
            rttr.addFlashAttribute("result", "회원 가입 완료");
        };
        return "redirect:/login";
    }

    //회원 정보를 수정하려면 회원 아이디를 넘겨 받아야됨.
    @GetMapping("/modify")
    public String modifyForm() {
        //service.check();
         return "/member/modify";
     }

    @PostMapping("/modify")
    public String modify(@ModelAttribute MemberVO member, RedirectAttributes rttr){
        log.info("==========modifyMmeber={}", member);
        if(service.modify(member)){
            rttr.addFlashAttribute("result", "회원 정보 수정 완료");
        };

        return "redirect:/";
    }




    /**
     *    ? 이메일 중복 체크면 이메일, 닉네임 중복 체크면 닉네임 이렇게 하나의 값만 받는데
     *    MemberVO를 파라미터로 받는게 맞을지
     *    아니면 이메일 체크, 닉네임 체크 메서드를 2개 만들어서 각각 파라미터 하나씩 받고 쿼리도 각각 만드는 게 좋을지...
     */
    @GetMapping("/duplicationCheck")
    @ResponseBody
    public int duplicationCheck(MemberVO member){
        log.info("==========duplication Check member={}", member);

        MemberVO findMember = service.check(member);
        if(findMember == null){
            //사용자가 입력한 이메일과 동일한 이메일 or 닉네임이 없는 경우.
            //조회된 값이 0이라는 의미.
            return 0;
        }
        //이메일이나 닉네임이 있는경우
        return  1;
    }




}
