package com.example.controller;

import com.example.entity.Member;
import com.example.redis.RedisUserMapper;
import com.example.repository.MemberRepository;
import com.example.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping(value = "/redis")
public class RedisController {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    RedisUserMapper redisUserMapper;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loging() {
        return "redis_login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginp(@ModelAttribute Member mem) {
        // 오라클 DB에 사용자 아이디, 암호가 일치하는 것이 있느지 확인
        // 만약에 있다면 redis session db에 추가하기
        Optional<Member> obj = memberRepository.findById(mem.getUserid());

        if (obj.isPresent()) {
            redisUserMapper.setUser("userid", obj.get().getUserid());
        }

        return "redis_login";
    }
}
