package com.estsoft.springproject.controller;

import com.estsoft.springproject.domain.dto.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Arrays;

@Controller
public class PageController {
    @GetMapping("/thymeleaf/example")
    public String show(Model model) {
        Person person = new Person(1L, "장나나", 20, Arrays.asList("달리기", "복싱", "웨이트"));

        model.addAttribute("person", person);
        model.addAttribute("today", LocalDateTime.now());

        return "examplePage"; // html 페이지
    }
}
