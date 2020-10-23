package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService myUserDetailsService;

    @Bean
    public BCryptPasswordEncoder BCE() {
        return new BCryptPasswordEncoder();
    }

    @Override

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(BCE());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //url 주소로 무시
        web.ignoring().antMatchers("/css/**", "/script/**", "/image/**", "/fonts/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //127.0.0.1:8080/security/admin/*
        http.authorizeRequests()
                .antMatchers("/security/admin", "/security/admin/*").hasAuthority("ADMIN")
                .antMatchers("/security/manager", "/security/manager/*").hasAnyAuthority("ADMIN", "MANAGER")
                .anyRequest().permitAll().and()

                .formLogin().loginPage("/security/login")
                .loginProcessingUrl("/security/loginProcess")
//                .usernameParameter("username")
                .passwordParameter("passwd")
                .permitAll()
                //.defaultSuccessUrl("/security/home") <=페이지 고정
                .successHandler(new CustomLoginSuccessHandler()) // 로그인 성공시 핸들러 호출됨
                .and()

                .logout().logoutUrl("/security/logout")
//                .logoutSuccessUrl("/security/home")
                .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                .invalidateHttpSession(true)
                .clearAuthentication(true) // 세션 클리어
                .permitAll().and()

                .exceptionHandling().accessDeniedPage("/security/page403");

//                http.csrf().disable();
    }
}