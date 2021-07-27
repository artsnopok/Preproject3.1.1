package com.example.crud_with_boot.dao;



import com.example.crud_with_boot.models.Role;

import java.util.List;

public interface RoleDao {
    List<Role> index();

    Role findRoleById(long id);

    boolean save(Role role);

    void update(Role role);

    void delete(long id);

    Role findRoleByName(String name);
}
