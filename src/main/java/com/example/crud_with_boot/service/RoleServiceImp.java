package com.example.crud_with_boot.service;

import com.example.crud_with_boot.dao.RoleDao;
import com.example.crud_with_boot.models.Role;
import com.example.crud_with_boot.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImp(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }

    @Override
    public Role findRoleById(long id) {
        return roleDao.findRoleById(id);
    }

    @Override
    public boolean save(Role role) {
        return roleDao.save(role);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public void delete(long id) {
        roleDao.delete(id);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleDao.findRoleByName(name);
    }

    @Override
    public void refreshRoles(User user) {
        Set<Role> refreshedRoles = new HashSet<>();
        user.getRoles().forEach(r -> refreshedRoles.add(roleDao.findRoleByName(r.getName())));
        user.setRoles(refreshedRoles);
    }
}
