package com.example.controller;

import com.example.mapper.BoardMapper;
import com.example.vo.BoardVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
    @Autowired
    BoardMapper mMapper;

    @RequestMapping(value = "/write" ,method = RequestMethod.POST)
    public String write(
            HttpServletRequest request,
            @ModelAttribute BoardVO board) {
        System.out.println(board);
        mMapper.boardWrite(board);
        return "redirect:" + request.getContextPath() + "/board/list";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String write() {
        return "write";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, HttpSession httpSession) {

        httpSession.setAttribute("BOARDHIT_SESSION", 1);

        List<BoardVO> list = mMapper.boardList();
        model.addAttribute("list", list);
        return "board_list";
    }

    @RequestMapping(value = "/content", method = RequestMethod.GET)
    public String content(HttpServletRequest request, HttpSession httpSession, @RequestParam(value = "no", defaultValue = "0") int no, Model model) {
        if (no == 0) {
            return "redirect:" + request.getContextPath() + "/board/list";
        }

        Integer chk = (Integer) httpSession.getAttribute("BOARDHIT_SESSION");
        if (chk != null) {
            if (chk == 1) {
                mMapper.boardUpdateHit(no);
                httpSession.setAttribute("BOARDHIT_SESSION", 0);
            }
        }
        BoardVO obj = mMapper.boardOne(no);

        int prev = mMapper.boardPrevNo(no);
        int next = mMapper.boardNextNo(no);

        model.addAttribute("obj", obj);
        model.addAttribute("prev", prev);
        model.addAttribute("next", next);

        return "board_content";
    }

    @RequestMapping(value = "/update" ,method = RequestMethod.GET)
    public String update(
            Model model,
            @RequestParam(value = "no", defaultValue = "0") int no) {
        try {
            BoardVO obj = mMapper.boardOne(no);
            model.addAttribute("obj", obj);

            return "board_update";
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    @RequestMapping(value = "/update" ,method = RequestMethod.POST)
    public String update(HttpServletRequest request,
                         @ModelAttribute BoardVO obj) {
        try {
            System.out.println(obj);
            mMapper.updateBoardOne(obj);
            return "redirect:" + request.getContextPath() + "/board/content?no=" + obj.getBrd_no();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/delete" ,method = RequestMethod.POST)
    public String update(HttpServletRequest request,
                         @RequestParam("no") int no) {
        try {
            System.out.println(no);
            mMapper.boardDeleteOne(no);
            return "redirect:" + request.getContextPath() + "/board/list";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
