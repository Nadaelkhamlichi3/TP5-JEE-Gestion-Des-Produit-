package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TestHibernate {

    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            Session session = sessionFactory.openSession();

            System.out.println("Connexion Hibernate reussie !");

            session.close();
            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}