package com.example.crud_with_boot.service;

import com.example.crud_with_boot.models.Role;
import com.example.crud_with_boot.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> index();
    User show(long id);
    boolean save(User user);
    boolean update(User user);
    void delete(long id);
    Role findRoleById(long id);
    Role findRoleByName(String name);
    User findUserByEmail(String email);
    void refreshRoles(User user);
    List<Role> getRoles();

}

