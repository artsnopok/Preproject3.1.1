package com.example.crud_with_boot.controllers;


import com.example.crud_with_boot.models.User;
import com.example.crud_with_boot.service.RoleService;
import com.example.crud_with_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(value = AppRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AppRestController {

    public static final String REST_URL = "/api/users";

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AppRestController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> showAllUsers() {
        return new ResponseEntity<>(userService.index(), HttpStatus.OK);
    }

    @GetMapping("/logged")
    public ResponseEntity<User> show(@AuthenticationPrincipal UserDetails user) {
        return new ResponseEntity<>(userService.findUserByEmail(user.getUsername()), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@RequestBody User user) {
        roleService.refreshRoles(user);
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody User user) {
        roleService.refreshRoles(user);
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        userService.delete(id);
    }


}
