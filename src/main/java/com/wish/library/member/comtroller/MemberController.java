package com.wish.library.member.comtroller;

import com.wish.library.member.domain.MemberVO;
import com.wish.library.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

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


    @GetMapping("/")
    public String home(Model model){
        //인증된 사용자의
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(email.equals("anonymousUser"))){
            log.info("index page, security 조회 email : " + email);
            MemberVO memberVO = service.get(email);
            memberVO.setPassword(null);
            model.addAttribute("member", memberVO);
        }

        return "index";
    }

    @GetMapping("/login")
    public String loginForm(){
        //로그인 되지 않은 상태이면 로그인 페이지를, 로그인 된 상태라면 /index를 보여준다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("로그인 정보 : " + authentication.toString());
        if(authentication instanceof AnonymousAuthenticationToken)
            return "/member/login";
        return "redirect:/";
    }


/*     @PostMapping("/login")
    public String login(@RequestParam("email") String email, String password , HttpSession session){

        MemberVO loginMember = service.login(email, password);
        log.info("==========loginMember={}", loginMember);
        if(loginMember == null){
            return "redirect:/login";
        }
        session.setAttribute("email", loginMember.getEmail());
        session.setAttribute("nickname", loginMember.getNickname());
        //redirect는 경로로 설정
        return "redirect:/";
    }*/
/*
    @PostMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        log.info("로그아웃");
        return "redirect:/";
    }*/


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
    //아래 방식은 세션을 이용한 방식
/*    @GetMapping("/modify")
    public String modifyForm(HttpSession session, Model model) {
         model.addAttribute("member", service.get((String)session.getAttribute("email")));
         return "/member/modify";
     }*/

    @GetMapping("/modify")
    public String modifyForm(Model model){
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("modify page, security 조회 email : " + email);
        MemberVO member = service.get(email);
        model.addAttribute("member", member);
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
    @GetMapping("/getEmail")
    @ResponseBody
    public int getEmail(String email){
        log.info("==========duplication EmailCheck member={}", email);

        MemberVO findMember = service.get(email);
        if(findMember == null){
            //사용자가 입력한 이메일과 동일한 이메일 or 닉네임이 없는 경우.
            //조회된 값이 0이라는 의미.
            return 0;
        }
        //이메일이나 닉네임이 있는경우
        return  1;
    }

    @GetMapping("/getNickname")
    @ResponseBody
    public int getNickname(String nickname){
        log.info("==========duplication NicknameCheck member={}", nickname);

        MemberVO findMember = service.get(nickname);
        if(findMember == null){
            //사용자가 입력한 이메일과 동일한 이메일 or 닉네임이 없는 경우.
            //조회된 값이 0이라는 의미.
            return 0;
        }
        //이메일이나 닉네임이 있는경우
        return  1;
    }





}
