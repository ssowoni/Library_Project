package com.wish.library.config;

import com.wish.library.member.domain.MemberVO;
import com.wish.library.member.service.MemberService;
import com.wish.library.member.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthProvider implements AuthenticationProvider {

    private final MemberServiceImpl service;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = (String) authentication.getPrincipal(); // 로그인 창에 입력한 email
        String password = (String) authentication.getCredentials(); //로그인 창에 입력한 password
        log.info("입력받은 email, password={},{}", email,password);

        PasswordEncoder passwordEncoder = service.passwordEncoder();
        UsernamePasswordAuthenticationToken token;
        MemberVO memberVO = service.getMemberByEmail(email);
        log.info("로그인 시 조회해온 정보"+ memberVO);

        if (memberVO != null && passwordEncoder.matches(password, memberVO.getPassword())) {
            List<GrantedAuthority> rolse = new ArrayList<>();
            rolse.add(new SimpleGrantedAuthority(memberVO.getRole().getAuth())); //권한 부여

            token = new UsernamePasswordAuthenticationToken(memberVO.getEmail(), null, rolse);
            //인증된 user 정보를 담아 SecurityContextHolder에 저장되는 token
            return token;
        }

        throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
