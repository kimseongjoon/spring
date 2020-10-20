package com.example.controller;

import com.example.mapper.Member1Mapper;
import com.example.security.SecurityUser;
import com.example.vo.Member1Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@Controller
@RequestMapping(value = "/security")
public class SecurityController {

    @Autowired
    Member1Mapper mapper;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(HttpServletRequest request, Model model) {
        return "security_home";
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join() {
        return "security_join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join1(@ModelAttribute Member1Vo mem,
                        HttpServletRequest request) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pw = passwordEncoder.encode(mem.getPassword());
        mem.setPassword(pw);

        mapper.memberJoin(mem);

        return "redirect:" + request.getContextPath() + "/security/home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {
        return "security_login";
    }

    @RequestMapping(value = "/page403", method = RequestMethod.GET)
    public String page403(HttpServletRequest request, Model model) {
        return "security_page403";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String manager(HttpServletRequest request, Model model) {
        return "security_admin";
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager(HttpServletRequest request, Model model, Authentication auth) {
        if (auth != null) {
            SecurityUser user = (SecurityUser) auth.getPrincipal();
            if (user != null) {
                System.out.println("아이디 : " + user.getUsername());
                System.out.println("이름 : " + user.getName());
                Collection<GrantedAuthority> roles = user.getAuthorities();

                for (GrantedAuthority role : roles) {
                    System.out.println(role.getAuthority());
                }
                System.out.println();
            }
        }
        return "security_manager";
    }
}
