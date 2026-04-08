package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("")
    public String test(){
        return "hello spring";
    }

    @GetMapping("/2")
    public String test2(){
        return "hello spring2";
    }

    @GetMapping("/3")
    public String test3(){
        return "hello spring3";
    }

    @GetMapping("4")
    public String test4(){
        return "hello spring4";
    }

    @GetMapping("5")
    public String test5(){
        return "hello spring5";
    }

    @GetMapping("6")
    public String test6(){
        return "hello spring6";
    }
}
