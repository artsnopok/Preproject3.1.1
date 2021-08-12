package com.example.crud_with_boot.models;

import com.example.crud_with_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.Collections;


@Component
public class DataLoader {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public DataLoader(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostConstruct
    public void loadData() {
        userService.save(new User("admin", "fakeadmin", 0, "admin@admin.in",
                "admin", Collections.singleton(new Role("ROLE_ADMIN"))));

        userService.save(new User("user", "fakeuser", 0, "user1@user.in",
                "user", Collections.singleton(new Role("ROLE_USER"))));
        userService.save(new User("user", "fakeuser", 0, "user2@user.in",
                "user", Collections.singleton(new Role("ROLE_USER"))));
        userService.save(new User("user", "fakeuser", 0, "user3@user.in",
                "user", Collections.singleton(new Role("ROLE_USER"))));
        userService.save(new User("user", "fakeuser", 0, "user4@user.in",
                "user", Collections.singleton(new Role("ROLE_USER"))));
        userService.save(new User("user", "fakeuser", 0, "user5@user.in",
                "user", Collections.singleton(new Role("ROLE_USER"))));
        userService.save(new User("user", "fakeuser", 0, "user6@user.in",
                "user", Collections.singleton(new Role("ROLE_USER"))));
        userService.save(new User("user", "fakeuser", 0, "user7@user.in",
                "user", Collections.singleton(new Role("ROLE_USER"))));


    }
}




