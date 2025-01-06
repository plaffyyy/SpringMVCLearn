package ru.zimin.springcourse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

}
