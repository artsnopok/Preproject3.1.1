package com.example.crud_with_boot.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping()
public class AppViewController {

    @GetMapping("/admin/users")
    public String index(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("authUser", user);
        return "adminpage";
    }

    @GetMapping("/user/{id}")
    public String show(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("authUser", user);
        return "showRoleUser";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
