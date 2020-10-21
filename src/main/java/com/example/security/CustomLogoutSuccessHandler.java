package com.example.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.ForwardLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import java.io.IOException;

//public class CustomLogoutSuccessHandler extends ForwardLogoutSuccessHandlert{
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String url = httpServletRequest.getContextPath() + "/security/home";
        httpServletResponse.sendRedirect(url);
//        return "redirect:" + httpServletRequest.getContextPath() + "/security/home";
    }
}
