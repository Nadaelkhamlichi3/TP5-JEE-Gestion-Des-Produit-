package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Role;
import util.HibernateUtil;

public class RoleDaoImpl implements RoleDao {

    @Override
    public void save(Role role) {
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(role);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Role findByName(String name) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();

            Query<Role> query = session.createQuery(
                    "from Role where name = :name", Role.class);
            query.setParameter("name", name.trim());

            return query.uniqueResult();
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Role> findAll() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("from Role", Role.class).list();
        } finally {
            if (session != null) session.close();
        }
    }
}