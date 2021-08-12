package com.example.crud_with_boot.models;

import com.example.crud_with_boot.dao.RoleDao;
import com.example.crud_with_boot.service.RoleService;
import com.example.crud_with_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.Collections;


@Component
public class DataLoader {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataLoader(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


        @PostConstruct
        public void loadData() {
            userService.save(new User("admin", "fakeadmin", 0, "admin@admin.in",
                    "admin", Collections.singleton(new Role("ROLE_ADMIN"))));

            userService.save(new User("user", "fakeuser", 0, "user@user.in",
                    "user", Collections.singleton(new Role("ROLE_USER"))));
        }

}




