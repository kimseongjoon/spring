package com.example.controller;

import com.example.entity.Member;
import com.example.redis.RedisUserMapper;
import com.example.repository.MemberRepository;
import com.example.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping(value = "/redis")
public class RedisController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RedisUserMapper redisUserMapper;


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homeg(Model model, HttpSession httpSession) {
        Member user = (Member) redisUserMapper.getUser(httpSession.getId());

        if (user != null) {
            model.addAttribute("userid", user.getUserid());
        }

        return "redis_home";
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loging() {
        return "redis_login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginp(@ModelAttribute Member mem,
                         HttpServletRequest request,
                         HttpSession httpSession) {
        // 오라클 DB에 사용자 아이디, 암호가 일치하는 것이 있는지 확인
        // 만약에 있다면 redis session db에 추가하기
        Optional<Member> obj = memberRepository.findById(mem.getUserid());

        if (obj.isPresent()) {
            redisUserMapper.setUser(httpSession.getId(), obj.get());

            return "redirect:" + request.getContextPath() + "/redis/home";
        }

        return "redirect:" + request.getContextPath() + "/redis/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession httpSession, HttpServletRequest request) {
        redisUserMapper.delUser(httpSession.getId());

        return "redirect:" + request.getContextPath() + "/redis/home";
    }
}
