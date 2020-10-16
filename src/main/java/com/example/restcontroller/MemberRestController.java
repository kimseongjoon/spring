package com.example.restcontroller;

import com.example.mapper.MemberMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/rest")
public class MemberRestController {

    @Autowired
    MemberMapper memberMapper;

    @RequestMapping(value = "/ismember.json",
            method = {RequestMethod.GET, RequestMethod.POST},
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.ALL_VALUE})
    public int isMember(@RequestParam(value = "id", defaultValue = "") String id) {
        return memberMapper.isMember(id);
    }
}
