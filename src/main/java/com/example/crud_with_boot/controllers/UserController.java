package com.example.crud_with_boot.controllers;

import com.example.crud_with_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    public String show(@PathVariable("id") int id, @AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("user", userService.show(id));
        model.addAttribute("role", userService.findRoleById(id));
        model.addAttribute("authUser", user);
        return "showRoleUser";
    }
}
