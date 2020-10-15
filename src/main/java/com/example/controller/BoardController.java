package com.example.controller;

import com.example.mapper.BoardMapper;
import com.example.vo.BoardVO;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping(value = "/board")
public class BoardController {
    @Autowired
    BoardMapper mMapper;

    @RequestMapping(value = "/write" ,method = RequestMethod.POST)
    public String write(
            HttpServletRequest request,
            @ModelAttribute BoardVO board,
            @RequestParam("tmp_img")MultipartFile[] imgs) throws IOException {
        System.out.println(board);
        if (imgs != null && imgs.length > 0) {
            for (MultipartFile img : imgs) {
                board.setBrd_img(img.getBytes());
            }
        }
        mMapper.boardWrite(board);
        return "redirect:" + request.getContextPath() + "/board/list";
    }

    @RequestMapping(value = "/write", method = RequestMethod.GET)
    public String write() {
        return "write";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model, HttpSession httpSession,
                       HttpServletRequest request,
                       @RequestParam(value = "page", defaultValue = "0", required = false) int page,
                       @RequestParam(value = "txt", defaultValue = "", required = false) String txt) {
        if (page == 0) {
            return "redirect:" + request.getContextPath() + "/board/list?page=1";
        }

        httpSession.setAttribute("BOARDHIT_SESSION", 1);

        int cnt = mMapper.boardCount(txt);

        int pageSize = 10;
        int start = (page - 1) * pageSize + 1;
        int end = page * pageSize;


        System.out.println(start + " " + end);

        List<BoardVO> list = mMapper.boardList(page * 10 - 9, page * 10, txt);


        model.addAttribute("list", list);
        model.addAttribute("cnt", (cnt - 1) / pageSize + 1);
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

    @RequestMapping(value = "/imgview", method = RequestMethod.GET)
    public ResponseEntity<byte[]> imgview(
            HttpServletRequest request,
            @RequestParam(value = "no", defaultValue = "0") int no
    ) throws IOException {
        try {
            if (no > 0) {
                BoardVO obj = mMapper.boardImg(no);

                if (obj.getBrd_img().length > 0) {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.IMAGE_PNG);
                    ResponseEntity<byte[]> ret = new ResponseEntity<>(obj.getBrd_img(), headers, HttpStatus.OK);

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
                headers.setContentType(MediaType.IMAGE_PNG);
                ResponseEntity<byte[]> ret = new ResponseEntity<>(tmp, headers, HttpStatus.OK);

                return ret;
            }

            return null;
        }
    }
}
