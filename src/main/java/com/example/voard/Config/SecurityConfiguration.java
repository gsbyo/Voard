package com.example.voard.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.voard.Security.CustomAccessDeniedHandler;
import com.example.voard.Security.CustomAuthenticationEntryPoint;
import com.example.voard.Service.MemberService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity // 1
@Configuration 
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter { // 2

  private final MemberService memberService;

  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(WebSecurity web) { // 4
    web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
  }



  @Override
  protected void configure(HttpSecurity http) throws Exception { // 5
    http
    .exceptionHandling()
    .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
    .accessDeniedHandler(new CustomAccessDeniedHandler())
       .and()
          .rememberMe()
          .key("gsbyo")
          .rememberMeParameter("remember-me")
          .tokenValiditySeconds(86400 * 30)
          .userDetailsService(memberService)
       .and()
          .csrf().disable()
          .authorizeRequests() // 6
            .antMatchers("/login", "/signUp", "/user", "/join", "/board").permitAll() // 누구나 접근 허용
            .antMatchers("/board/*").hasRole("USER") // USER, ADMIN만 접근 가능
            .antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
            .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
        .and() 
          .formLogin() // 7
            .loginPage("/login") // 로그인 페이지 링크
            .defaultSuccessUrl("/board") // 로그인 성공 후 리다이렉트 주소
        .and()
          .logout() // 8
            .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
	    .invalidateHttpSession(true) // 세션 날리기
    ;
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
  }
}