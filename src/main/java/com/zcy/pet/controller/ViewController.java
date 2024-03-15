package com.zcy.pet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bbs")
public class ViewController {
    @GetMapping("/index")
    public String index(@RequestParam String userId) {
        System.out.println(userId);
        //return "redirect:/index.html/#/bbs/index.html?userId=" + userId;
        return "/bbs/index";
    }
}
