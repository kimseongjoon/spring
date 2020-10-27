package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.mapper.MemberMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.vo.MemberVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

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
			@ModelAttribute MemberVO mem,
			@RequestParam("tmp_img")MultipartFile[] imgs) throws IOException {
		System.out.print(mem.toString());
		if (imgs != null && imgs.length > 0) {
			for (MultipartFile img : imgs) {
				mem.setUserimg(img.getBytes());
			}
		}
		int ret = mMapper.memberJoin(mem);
		return "redirect:" + request.getContextPath() + "/member/login";
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

	@RequestMapping(value = "/imgview", method = RequestMethod.GET)
	public ResponseEntity<byte[]> imgview(
			HttpServletRequest request,
			@RequestParam(value = "id", defaultValue = "0") String id
	) throws IOException {
		try {
			if (id != null) {
				MemberVO obj = mMapper.memberImg(id);

				if (obj.getUserimg().length > 0) {
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.IMAGE_JPEG);
					ResponseEntity<byte[]> ret = new ResponseEntity<>(obj.getUserimg(), headers, HttpStatus.OK);

					return ret;
				}
			}

			return null;
		} catch (Exception e) {
			System.out.println("ERROR:" + e.getMessage());

			InputStream in = request.getServletContext().getResourceAsStream("/resources/img/default.png");
			byte[] tmp = IOUtils.toByteArray(in);

			if (tmp.length > 0) {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.IMAGE_JPEG);
				ResponseEntity<byte[]> ret = new ResponseEntity<>(tmp, headers, HttpStatus.OK);

				return ret;
			}

			return null;
		}

	}
}
