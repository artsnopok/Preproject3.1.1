package com.example.crud_with_boot.service;

import com.example.crud_with_boot.dao.RoleDao;
import com.example.crud_with_boot.dao.UserDao;
import com.example.crud_with_boot.models.Role;
import com.example.crud_with_boot.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImp(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public List<User> index() {
        return userDao.index();
    }

    @Override
    public User show(long id) {
        return userDao.show(id);
    }

    @Override
    public boolean save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.save(user);
        return true;
    }

    @Override
    public boolean update(User user) {
        if (user.getPassword().equals(userDao.show(user.getId()).getPassword())) {
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userDao.update(user);
        return true;
    }

    @Override
    public void delete(long id) {
        userDao.delete(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userDao.findUserByEmail(email);
    }

    @Override
    public Role findRoleById(long id) {
        return roleDao.findRoleById(id);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleDao.findRoleByName(name);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    public void refreshRoles(User user) {
        Set<Role> refreshedRoles = new HashSet<>();
        user.getRoles().forEach(r -> refreshedRoles.add(roleDao.findRoleByName(r.getName())));
        user.setRoles(refreshedRoles);
    }
}
