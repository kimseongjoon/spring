package com.example.security;

import com.example.demo.Properties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

//@Component
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /*@Value("${member.admin.name}") private String ADMINNAME;
    @Value("${member.manager.name}") private String MANAGERNAME;
    @Value("${member.user.name}") private String USERNAME;*/

    CustomLoginSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        SecurityUser user = (SecurityUser) authentication.getPrincipal();
        String page = "/home";
        if (user != null) {
            Collection<GrantedAuthority> roles = user.getAuthorities();

            for (GrantedAuthority role : roles) {
                if (role.getAuthority().equals(Properties.ADMINNAME)) {
                    page = "/admin";
                } else if (role.getAuthority().equals(Properties.MANAGERNAME)) {
                    page = "/manager";
                } else if (role.getAuthority().equals(Properties.USERNAME)) {
                    page = "/user";
                }
            }
        }

        String url = request.getContextPath() + "/security" + page;
        getRedirectStrategy().sendRedirect(request, response, url);
    }
}
