package web.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

//   @Autowired
//   private SessionFactory sessionFactory;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public void add(User user) {
        // sessionFactory.getCurrentSession().save(user);
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        //entityManager.merge();
        entityManager.merge(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public List<User> getUsers() {
        //TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getUserByName(String name) {

        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.firstName = :name ", User.class);
        User user = query.setParameter("name", name)
                .getSingleResult();
        // return entityManager.find(User.class, name);
//        List<User> users = query.setParameter("username", username)
//                .setParameter("number", number)
//                .getResultList();

//        Query jpqlQuery = getEntityManager().createQuery("SELECT u FROM UserEntity u WHERE u.id=:id");
//        jpqlQuery.setParameter("id", id);
//        return (UserEntity) jpqlQuery.getSingleResult();

        return user;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }
}
