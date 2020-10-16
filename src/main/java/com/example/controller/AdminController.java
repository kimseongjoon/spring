package com.example.controller;

import com.example.mapper.MemberMapper;
import com.example.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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
                          @RequestParam("btn") String btnName,
                          @RequestParam(value = "chk[]", required = false) String[] id) {
        if (btnName.equals("일괄삭제")) {
            mapper.memberBatchDelete(id);
        } else if (btnName.equals("일괄수정")){

        }

        return "redirect:" + request.getContextPath() + "/admin/home?menu=4";
    }
}