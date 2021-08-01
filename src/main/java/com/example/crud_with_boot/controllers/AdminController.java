package com.example.crud_with_boot.controllers;


import com.example.crud_with_boot.models.User;
import com.example.crud_with_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@Controller
@RequestMapping("/admin/")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users")
    public String index(@AuthenticationPrincipal UserDetails user, Model model) {
        model.addAttribute("users", userService.index());
        model.addAttribute("roleAdmin", userService.findRoleById(1L));
        model.addAttribute("roleUser", userService.findRoleById(2L));
        model.addAttribute("roles", userService.getRoles());
        model.addAttribute("authUser", user);
        model.addAttribute("user", new User());
        return "adminpage";
    }

    @GetMapping("users/{id}")
    public String show(@PathVariable("id") int id, Model model, @AuthenticationPrincipal UserDetails user) {
        model.addAttribute("user", userService.show(id));
        model.addAttribute("role", userService.findRoleById(id));
        model.addAttribute("roles", userService.getRoles());
        model.addAttribute("authUser", user);
        model.addAttribute("userInfo", userService.findUserByEmail(user.getUsername()));
        return "show";
    }

    @PutMapping("update")
    public String editUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin/users";
    }
//    @GetMapping("new")
//    public String newPerson(@ModelAttribute("user") User user,
//                            Model model) {
//        model.addAttribute("roleAdmin", userService.findRoleById(1L));
//        model.addAttribute("roleUser", userService.findRoleById(2L));
//        return "new";
//    }

    @PostMapping("/users")
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("roleAdmin", userService.findRoleById(1));
            model.addAttribute("roleUser", userService.findRoleById(2));
            return "new";
        }
        userService.refreshRoles(user);
        userService.save(user);
        return "redirect:/admin/users";
    }

//    @GetMapping("users/{id}/edit")
//    public String edit(@PathVariable("id") int id, Model model) {
//        model.addAttribute("user", userService.show(id));
//        model.addAttribute("roleAdmin", userService.findRoleById(1));
//        model.addAttribute("roleUser", userService.findRoleById(2));
//        return "edit";
//    }
//
//    @PatchMapping("users/{id}")
//    public String update(@ModelAttribute("user") @Valid User user,
//                         BindingResult bindingResult, Model model) {
//        if (bindingResult.hasErrors()) {
//            model.addAttribute("roleAdmin", userService.findRoleById(1));
//            model.addAttribute("roleUser", userService.findRoleById(2));
//            return "edit";
//        }
//        userService.refreshRoles(user);
//        userService.update(user);
//        return "redirect:/admin/users";
//    }

//    @DeleteMapping("users/{id}")
//    public String delete(@PathVariable("id") int id) {
//        userService.delete(id);
//        return "redirect:/admin/users";
//    }

    @DeleteMapping("users/delete")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.delete(user.getId());
        return "redirect:/admin/users";
    }

}
