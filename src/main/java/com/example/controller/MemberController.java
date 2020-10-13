package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.vo.MemberVO;

@Controller
@RequestMapping(value="/member")
public class MemberController {

	@Autowired
	MemberMapper mMapper; // MemberMapper mMapper = new MemberMapper();

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join1(
			HttpServletRequest request,
			@ModelAttribute MemberVO mem ) {
		System.out.print(mem.toString());
		int ret = mMapper.memberJoin(mem);
		return "redirect:" + request.getContextPath() + "/member/join";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginp(@ModelAttribute MemberVO mem, HttpServletRequest request, HttpSession httpSession) {
		System.out.println(mem);

		MemberVO obj = mMapper.memberLogin(mem);
		if (obj == null) {
			return "redirect:" + request.getContextPath() + "/member/login";
		}

		httpSession.setAttribute("USERID_SESSION", obj.getUserid());
		return "redirect:" + request.getContextPath() + "/home";
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession httpSession, HttpServletRequest request) {
		httpSession.setAttribute("USERID_SESSION", null);
		httpSession.invalidate();
		return "redirect:" + request.getContextPath() + "/home";
	}
}
