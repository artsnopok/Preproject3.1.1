package com.example.crud_with_boot.service;

import com.example.crud_with_boot.models.Role;
import com.example.crud_with_boot.models.User;

import java.util.List;

public interface RoleService {
    List<Role> getRoles();

    Role findRoleById(long id);

    boolean save(Role role);

    void update(Role role);

    void delete(long id);

    Role findRoleByName(String name);

    void refreshRoles(User user);
}
