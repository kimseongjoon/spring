package com.example.controller;

import com.example.entity.Item;
import com.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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

    @RequestMapping(value = "/item_select.do", method = RequestMethod.GET)
    public String select(HttpServletRequest request, Model model) {
//        Iterable<Item> list = itemRepository.findAll();
        List<Item> list = itemRepository.findAllByOrderByItmnoDesc();
//        List<Item> list = itemRepository.sqlOrderBynoDesc();

        model.addAttribute("list", list);
        return "item_select";
    }

    @RequestMapping(value = "/item_update.do", method = RequestMethod.GET)
    public String update(@RequestParam(value = "itmno", defaultValue = "0") long itmno, Model model, HttpServletRequest request) {
        if (itmno != 0) {
            Optional<Item> item = itemRepository.findById(itmno);

            model.addAttribute("item", item.get());
        }

        return "item_update";
    }

    @RequestMapping(value = "item_update.do", method = RequestMethod.POST)
    public String update1(@ModelAttribute Item item, HttpServletRequest request) {
        itemRepository.save(item);
//        itemRepository.sqlUpdateByNo(item);

        return "redirect:" + request.getContextPath() + "item_select.do";
    }


    @RequestMapping(value = "/item_delete.do", method = RequestMethod.GET)
    public String delete(HttpServletRequest request, @RequestParam(value = "itmno", defaultValue = "0") long itmno) {
        if (itmno != 0) {
//            itemRepository.deleteById(itmno);
            itemRepository.sqlDeleteByNo(itmno);
        }

        return "item_home";
    }
}
