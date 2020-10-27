package com.example.restcontroller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("rest")
public class ReactRestController {
//    @GetMapping(value = "/test1.json")
    @RequestMapping(value = "/test1.json", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE},
    consumes = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> test1g(
            @RequestParam("name") String name,
            @RequestParam("age") int age
    ) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "가나다" + name);
        map.put("userage", 123 + age);

        return map;
    }

    // {name:'홍길동', age:234}
    @RequestMapping(value = "/test1.json", method = RequestMethod.POST, produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public Map<String, Object> test1p(
            @RequestBody Map<String, Object> requestMap
    ) {
        String name = (String) requestMap.get("name"); // 홍길동
        int age = (int) requestMap.get("age");

        Map<String, Object> map = new HashMap<>();
        map.put("username", "가나다" + name);
        map.put("userage", 123 + age);

        return map;
    }
}
