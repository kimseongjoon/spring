package com.example.restcontroller;

import com.example.mapper.BoardMapper;
import com.example.vo.BoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/rest")
public class BoardRestController {

    @Autowired
    BoardMapper boardMapper;

    @RequestMapping(value = "/boardone.json",
            method = {RequestMethod.GET, RequestMethod.POST},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> boardone(@RequestParam(value = "no", defaultValue = "0") int no) {
        /*HashMap<String, Object> map = new HashMap<>();

        map.put("userid", "aaa");
        map.put("userage", 23);
        map.put("username", "홍길동");

        return map;*/
        BoardVO obj = boardMapper.boardOne1(no);

        HashMap<String, Object> map = new HashMap<>();
        map.put("data", obj);

        return map;
    }

    // http://localhost:8080/rest/boardlist.json?page=1&txt=
    // 목록이 나타나도록 restcontroller 작성
    @RequestMapping(value = "/boardlist.json",
            method = {RequestMethod.GET, RequestMethod.POST},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public HashMap<String, Object> boardlist(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
                                             @RequestParam(value = "txt", defaultValue = "", required = false) String txt) {
        int pageSize = 10;
        int start = (page * pageSize) - (pageSize - 1);
        int end = page * pageSize;

        int cnt = boardMapper.boardCount(txt);

        List<BoardVO> list =  boardMapper.boardList(start, end, txt);

        HashMap<String, Object> map = new HashMap<>();
        map.put("list", list);

        return map;
    }
}
