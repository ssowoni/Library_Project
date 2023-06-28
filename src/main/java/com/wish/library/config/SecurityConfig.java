package com.wish.library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{



        //권한에 따라 허용하는 url 설정
        http.authorizeRequests()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/","/login","/join").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/member/**").hasRole("MEMBER")
                .anyRequest().authenticated(); // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야만 접근 가능

        //login 설정
        http.formLogin()
                .loginPage("/login") // GET 요청 (login form을 보여줌)
                .loginProcessingUrl("/login") //POST 요청 (login 창에 입력한 데이터를 처리)
                .usernameParameter("email") //login에 필요한 id 값을 email로 설정 (default는 username)
                .passwordParameter("password") //login에 필요한 password 값을 password
                .defaultSuccessUrl("/"); //login에 성공하면 /로 redirect

        //logout 설정
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/"); //logout에 성공하면 /로 redirect

        return http.build();
    }








}
