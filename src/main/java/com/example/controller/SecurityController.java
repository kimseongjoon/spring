package com.example.controller;

import com.example.mapper.Member1Mapper;
import com.example.vo.Member1Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

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
}
