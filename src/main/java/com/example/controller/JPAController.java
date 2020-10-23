package com.example.controller;

import com.example.entity.Item;
import com.example.entity.ItemImg;
import com.example.repository.ItemImgRepository;
import com.example.repository.ItemQueryRepository;
import com.example.repository.ItemRepository;
import com.fasterxml.jackson.core.json.async.NonBlockingJsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/jpa")
public class JPAController {

    @Autowired
    ItemRepository itemRepository;

    /*@Autowired
    ItemQueryRepository itemQueryRepository;*/

    @Autowired
    ItemImgRepository imgRepository;

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
        itemRepository.save(obj);
        return "redirect:" + request.getContextPath() + "/jpa/home";
    }

    @RequestMapping(value = "/item_select.do", method = RequestMethod.GET)
    public String select(HttpServletRequest request, Model model,
                         @RequestParam(value = "name", defaultValue = "") String name,
                         @RequestParam(value = "page", defaultValue = "1") int page) {

//        Iterable<Item> list = itemRepository.findAll();
//        List<Item> list = itemRepository.findAllByOrderByItmnoDesc();



        PageRequest pageable = PageRequest.of(page - 1, 10);

        List<Item> list = itemRepository.findAllByItmnameIgnoreCaseContainingOrderByItmnoAsc(name, pageable);
        long tot = itemRepository.countByItmnameIgnoreCaseContaining(name);
//        List<Item> list = itemRepository.sqlOrderBynoDesc();


        model.addAttribute("list", list);
        model.addAttribute("tot", (tot - 1) / 10 + 1);
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

    @RequestMapping(value = "/item_search.do", method = RequestMethod.GET)
    public String search(@RequestParam(value = "word", defaultValue = "") String word, Model model) {
       /* List<Item> list = itemQueryRepository.findByItmname(word);

        model.addAttribute("list", list);*/
        return "item_select";
    }

    @RequestMapping(value = "/item_detail.do", method = RequestMethod.GET)
    public String detail(@RequestParam(value = "itmno") long itmno, Model model) {
        /*Item obj = itemRepository.findAllByItmno(itmno);

        model.addAttribute("obj", obj);*/

        Optional<Item> obj = itemRepository.findById(itmno);
        model.addAttribute("obj", obj.get());

        List<ItemImg> list = imgRepository.findAllByItmno(itmno);

        for (ItemImg itemImg : list) {
            byte[] imgArray = itemImg.getImg();
            String imgStr = Base64.getEncoder().encodeToString(imgArray);
            itemImg.setStrImg(imgStr);
            itemImg.setImg(null);
        }

        model.addAttribute("list", list);
        /*String[] imgs = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            // byte[] -> encoding -> String
            imgs[i] = Base64.getEncoder().encodeToString(list.get(i).getImg());
        }

        model.addAttribute("imgs", imgs);*/


        return "item_detail";
    }

    @RequestMapping(value = "/item_image_insert.do", method = RequestMethod.POST)
    public String image(HttpServletRequest request, @RequestParam(value = "no", defaultValue = "0") long no,
                        @RequestParam(value = "img1") MultipartFile[] files) throws IOException {
        for (MultipartFile tmp : files) {
            if (!tmp.isEmpty()) {
                ItemImg obj = new ItemImg();
                obj.setItmno(no);
                obj.setImg(tmp.getBytes());

                imgRepository.save(obj);
            }
        }

        return "redirect:" + request.getContextPath() + "/jpa/item_detail.do?itmno=" + no;
    }

    @RequestMapping(value = "/item_image_delete.do", method = RequestMethod.GET)
    public String delImage(HttpServletRequest request,
                           @RequestParam(value = "no") long no,
                           @RequestParam(value = "itmno") long itmno) {
        imgRepository.deleteById(no);

        return "redirect:" + request.getContextPath() + "/jpa/item_detail.do?itmno=" + itmno;
    }
}
