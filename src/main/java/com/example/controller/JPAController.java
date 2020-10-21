package com.example.controller;

import com.example.entity.Item;
import com.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/jpa")
public class JPAController {

    @Autowired
    ItemRepository itemRepository;

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home() {
        return "item_home";
    }
    // 1. item_insert.jsp를 만들고 결과값이 전달되어 화면에 출력되는것 구현하시오.
    @RequestMapping(value = "/item_insert.do", method = RequestMethod.GET)
    public String insert(HttpServletRequest request, Model model) {
        return "item_insert";
    }

    @RequestMapping(value = "/item_insert.do", method = RequestMethod.POST)
    public String insertp(HttpServletRequest request, Model model, @ModelAttribute Item obj) {

        System.out.println(obj);
        itemRepository.save(obj);
        return "redirect:" + request.getContextPath() + "/jpa/home";
    }
}
