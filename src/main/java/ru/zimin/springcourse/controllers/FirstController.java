package ru.zimin.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FirstController {

    @GetMapping("/hi")
    public String helloPage() {
        return "first/hello";
    }
    @GetMapping("/bye")
    public String goodByePage() {
        return "first/goodbye";
    }

}
