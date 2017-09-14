package com.mr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @GetMapping("/checkSession/{sessionId}")
    public Map checkSessionId(@PathVariable String sessionId) {
        Map map = new HashMap();
        map.put("status", sessionId.equals("1") ? "valid" : "invalid");
        return map;
    }
}
