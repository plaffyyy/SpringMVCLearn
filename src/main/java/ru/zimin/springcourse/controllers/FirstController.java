package ru.zimin.springcourse.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FirstController {
    @GetMapping("/")
    public String firstPage() {
        return "first/first";
    }
    @GetMapping("/hi")
    public String helloPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            Model model
    ) {

        System.out.println("Hello, " + name + " " + surname);

        return "first/hello";
    }
    @GetMapping("/bye")
    public String goodByePage() {
        return "first/goodbye";
    }

}
