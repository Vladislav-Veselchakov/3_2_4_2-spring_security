package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void add(Role role) {
        entityManager.persist(role);

    }

    @Override
    @Transactional
    public void update(Role role) {
        entityManager.merge(role);
    }

    @Override
    @SuppressWarnings("Unchecked")
    @Transactional
    public List<Role> getRoles() {
        return entityManager.createQuery("SELECT r FROM Role r").getResultList();

    }

    @Override
    @Transactional
    public void deleteRole(Long id) {
        Role role = entityManager.find(Role.class, id);
        entityManager.remove(role);

    }

    @Override
    @Transactional
    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);

    }
}
