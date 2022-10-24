package service;

import dao.UserDAO;
import entity.User;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class UserService implements UserDAO {

    Session session;

    UserService(Session session) {
        this.session = session;
    }

    @Override
    public void add(User user) throws SQLException {

        session.beginTransaction();
        session.persist(user);
        session.getTransaction().commit();

    }

    @Override
    public List<User> getAllUsers() throws SQLException {

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);

        TypedQuery<User> allQuery = session.createQuery(all);

        return allQuery.getResultList();
    }

    @Override
    public User getById(int id) throws SQLException {

        User user;

        session.beginTransaction();
        user = session.get(User.class, id);
        session.getTransaction().commit();

        return user;
    }

    @Override
    public void update(User user) throws SQLException {

        if(user == null) return;

        session.beginTransaction();
        session.merge(user);
        session.getTransaction().commit();

    }

    @Override
    public void remove(User user) throws SQLException {

        if(user == null) return;

        session.beginTransaction();
        session.remove(user);
        session.getTransaction().commit();

    }
}
