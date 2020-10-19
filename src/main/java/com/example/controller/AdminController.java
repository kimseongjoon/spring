package com.example.controller;

import com.example.mapper.MemberMapper;
import com.example.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    MemberMapper mapper;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(HttpServletRequest request,
                       Model model,
                       @RequestParam(value = "menu", defaultValue = "0", required = false) int menu) {
        if (menu == 0) {
            return "redirect:" + request.getContextPath() + "/admin/home?menu=2";
        } else if (menu == 4) {
            List<MemberVO> list = mapper.memberList();

            model.addAttribute("list", list);
        }

        return "admin_home";
    }


    @RequestMapping(value = "/batchinsert", method = RequestMethod.POST)
    public String batchinsert(HttpServletRequest request,
                              @RequestParam(value = "id[]") String[] id,
                              @RequestParam(value = "pw[]") String[] pw,
                              @RequestParam(value = "name[]") String[] name,
                              @RequestParam(value = "phone[]") String[] phone,
                              @RequestParam(value = "age[]") int[] age) {

        List<MemberVO> list = new ArrayList<>();

        for (int i = 0; i < id.length; i++) {
            MemberVO tmp = new MemberVO();
            tmp.setUserid(id[i]);
            tmp.setUserpw(pw[i]);
            tmp.setUsername(name[i]);
            tmp.setUserphone(phone[i]);
            tmp.setUserage(age[i]);

            list.add(tmp);
        }

        mapper.memberBatchInsert(list);

        return "redirect:" + request.getContextPath() + "/admin/home?menu=2";
    }

    @RequestMapping(value = "batchupdatedelete", method = RequestMethod.POST)
    public String batchud(HttpServletRequest request,
                          RedirectAttributes redirectAttributes,
                          @RequestParam("btn") String btnName,
                          @RequestParam(value = "chk[]", required = false) String[] id) {
        if (btnName.equals("일괄삭제")) {
            mapper.memberBatchDelete(id);
        } else if (btnName.equals("일괄수정")) {
            redirectAttributes.addFlashAttribute("ids", id); // post로 보냄( 배열은 get으로 보낼 수 없어서)
            return "redirect:" + request.getContextPath() + "/admin/update";
        }

        return "redirect:" + request.getContextPath() + "/admin/home?menu=4";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String home(HttpServletRequest request,
                       Model model) {
//        1. 이전 post에서 넘긴 값 받기
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if (map == null) {
            return "redirect:" + request.getContextPath() + "/admin/home?menu=4";
        }
        String[] chk = (String[]) map.get("ids");

//        2. 회원아이디가 일치하는 것만 목록으로 가져오기
        List<MemberVO> list =  mapper.memberListByIds(chk);

//        3. jsp로 넘겨서 table로 출력하기
        model.addAttribute("list", list);
        return "admin_update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updatepost(HttpServletRequest request, Model model,
                             @RequestParam(value = "id[]") String[] id,
                             @RequestParam(value = "pw[]") String[] pw,
                             @RequestParam(value = "name[]") String[] name,
                             @RequestParam(value = "phone[]") String[] phone,
                             @RequestParam(value = "age[]") int[] age) {

        System.out.println("udpate post");
        List<MemberVO> list = new ArrayList<>();

        for (int i = 0; i < id.length; i++) {
            MemberVO tmp = new MemberVO();
            tmp.setUserid(id[i]);
            tmp.setUserpw(pw[i]);
            tmp.setUsername(name[i]);
            tmp.setUserphone(phone[i]);
            tmp.setUserage(age[i]);
            list.add(tmp);
        }


        for (MemberVO memberVO : list) {
            System.out.println(memberVO);
        }

        mapper.memberBatchUpdate(list);

        return "redirect:" + request.getContextPath() + "/admin/home?menu=4";
    }
}