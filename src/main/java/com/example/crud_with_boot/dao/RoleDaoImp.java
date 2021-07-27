package com.example.crud_with_boot.dao;

import com.example.crud_with_boot.models.Role;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Component
@Transactional(readOnly = true)
public class RoleDaoImp implements RoleDao {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> index() {
        return entityManager.createQuery("from Role").getResultList();
    }

    @Override
    public Role findRoleById(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    @Transactional
    public boolean save(Role role) {
        entityManager.persist(role);
        return true;
    }

    @Override
    @Transactional
    public void update(Role role) {
        entityManager.merge(role);
    }

    @Override
    @Transactional
    public void delete(long id) {
        entityManager.remove(entityManager.find(Role.class, id));
    }

    @Override
    public Role findRoleByName(String name) {
        return entityManager.createQuery("from Role where name = :name", Role.class)
                .setParameter("name", name)
                .getResultList()
                .stream()
                .findAny()
                .orElse(null);

    }
}
