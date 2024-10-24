package com.estsoft.springproject.user.controller;

import com.estsoft.springproject.user.domain.dto.AddUserRequest;
import com.estsoft.springproject.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    // "/?email=JJY@gmail.com&password=10" 로 요청 시 객체로 바인딩
    // like @RequestParam 1:1로 맵핑이냐 객체로 맵핑이냐 차이
    @PostMapping("/user")
    public String signup(@ModelAttribute AddUserRequest request) {
        service.save(request);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}
