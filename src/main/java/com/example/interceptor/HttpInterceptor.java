package com.example.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class HttpInterceptor extends HandlerInterceptorAdapter {
    /**
     * This implementation is empty.
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String path = request.getServletPath();
        String query = request.getQueryString();

        if (query == null) {
            System.out.println("INTERCEPTOR : " + path);        // 세션 필요시

            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("CURR_URL", path);
        } else {
            System.out.println("INTERCEPTOR : " + path + "?" + query);        // 세션 필요시

            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("CURR_URL", path + "?" + query);
        }
    }
}
