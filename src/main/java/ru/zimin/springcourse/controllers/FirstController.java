package ru.zimin.springcourse.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zimin.springcourse.javaclasses.ActionType;


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

//        System.out.println("Hello, " + name + " " + surname);
        model.addAttribute("message", "Hello, " + name + " " + surname);

        return "first/hello";
    }
    @GetMapping("/bye")
    public String goodByePage() {
        return "first/goodbye";
    }
//    http://localhost:8080/calculator?a=2&b=3&action=addition
    @GetMapping("/calculator")
    public String calculatorPage(
            @RequestParam(value = "a", required = false) long firstNum,
            @RequestParam(value = "b", required = false) long secondNum,
            @RequestParam(value = "action", required = false) String operation,
            Model model
    ) {

        model.addAttribute("firstNum", firstNum);
        model.addAttribute("secondNum", secondNum);
        model.addAttribute("operation", new ActionType(operation).getOperation());
        model.addAttribute("result", switch (operation) {
            case ("multiplication") -> firstNum * secondNum;
            case ("division") -> firstNum / secondNum;
            case ("addition") -> firstNum + secondNum;
            case ("subtraction") -> firstNum - secondNum;

            default -> throw new IllegalStateException("Unexpected value: " + operation);
        });


        return "first/calculator";
    }

}
