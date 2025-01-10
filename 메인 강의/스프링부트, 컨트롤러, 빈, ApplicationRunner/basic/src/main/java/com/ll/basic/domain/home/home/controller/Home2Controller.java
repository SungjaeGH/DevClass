package com.ll.basic.domain.home.home.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home2")
public class Home2Controller {

    private final List<Integer> ages;
    private final List<Integer> ages2;

    @GetMapping("/ages")
    @ResponseBody
    public List<Integer> ages() {
        return ages;
    }

    @GetMapping("/ages2")
    @ResponseBody
    public List<Integer> ages2() {
        return ages2;
    }
}

