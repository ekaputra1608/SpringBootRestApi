package com.mr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public Map welcome() {
        Map map = new HashMap();
        map.put("message", "welcome");
        return map;
    }
}
