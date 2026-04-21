package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Role;
import model.User;
import util.HibernateUtil;

public class UserDaoImpl implements UserDao {

    @Override
    public void save(User user) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void update(User user) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public void delete(Long id) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }

            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public User findById(Long id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.get(User.class, id);
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public User findByUsername(String username) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Query<User> query = session.createQuery(
                    "select u from User u join fetch u.role where u.username = :username",
                    User.class);
            query.setParameter("username", username.trim());

            return query.uniqueResult();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Query<User> query = session.createQuery(
                    "select u from User u join fetch u.role where u.username = :username and u.password = :password",
                    User.class);
            query.setParameter("username", username.trim());
            query.setParameter("password", password.trim());

            return query.uniqueResult();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Role findRoleByName(String roleName) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Query<Role> query = session.createQuery(
                    "from Role where name = :name", Role.class);
            query.setParameter("name", roleName.trim());

            return query.uniqueResult();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<User> findAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("from User", User.class).list();
        } finally {
            if (session != null) session.close();
        }
    }
}