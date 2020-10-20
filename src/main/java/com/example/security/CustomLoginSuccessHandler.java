package com.example.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        String page = "/home";
        if (user != null) {
            System.out.println("아이디 : " + user.getUsername());
            System.out.println("이름 : " + user.getName());
            Collection<GrantedAuthority> roles = user.getAuthorities();

            for (GrantedAuthority role : roles) {
                if (role.getAuthority().equals("ADMIN")) {
                    page = "/admin";
                } else if (role.getAuthority().equals("MANAGER")) {
                    page = "/manager";
                } else if (role.getAuthority().equals("USER")) {
                    page = "/user";
                }
                System.out.println(role.getAuthority());
            }
            System.out.println();
        }

        String url = request.getContextPath() + "/security" + page;
        getRedirectStrategy().sendRedirect(request, response, url);
    }
}
