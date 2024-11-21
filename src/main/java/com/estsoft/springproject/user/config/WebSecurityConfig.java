package com.estsoft.springproject.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@Configuration
public class WebSecurityConfig {

    // 특정 요청은 시큐리티 설정을 거치지 않도록 ignore 처리
    @Bean
    public WebSecurityCustomizer ignore() {
        return webSecurity -> webSecurity.ignoring()
//                .requestMatchers(toH2Console())
                .requestMatchers("/static/**", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html");
    }


    // 패스워드를 암호화 할 수 있는 인코더 빈 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    // 인증관리자 관련 설정
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) {
//        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userDetailService)  // 8) 사용자 정보 서비스 설정
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();
//    }

//    // 구 버전
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity.authorizeHttpRequests()
//                .requestMatchers("/login", "/signup", "/user").permitAll()
////                .requestMatchers("/api/external").hasRole("admin") // 인가
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()        //4) 폼 기반 로그인 설정
//                .loginPage("/login") // 로그인 성공할 경우 리디렉션
//                .defaultSuccessUrl("/articles")
//                .and()
//                .logout()       // 5) 로그아웃 설정
//                .logoutSuccessUrl("/login")
//                .invalidateHttpSession(true)
//                .and()
//                .csrf().disable()       // 6) csrf 비활성화
//                .build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(custom -> custom
                        .requestMatchers("/login", "/signup", "/user").permitAll()
//                        .requestMatchers("/articles","/api/articles").hasRole("ADMIN")
                        .anyRequest()
//                        .authenticated()
                                .permitAll()
                )
                .formLogin(custom -> custom
                        .loginPage("/login")
                        .defaultSuccessUrl("/articles", true)
                )
                .logout(custom -> custom
                    .logoutUrl("/logout")
                        .logoutSuccessUrl("/articles")
                        .invalidateHttpSession(true)
                )
                .csrf(custom -> custom.disable())
                .build();
    }

}

