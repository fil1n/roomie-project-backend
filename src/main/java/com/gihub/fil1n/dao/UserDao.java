package com.gihub.fil1n.handlers;

import com.gihub.fil1n.HibernateInit;
import com.gihub.fil1n.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;

public class UserDao {

    private Session session;
    private Transaction transaction;

    public User getById(@NotNull Long id) throws Exception {

        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User result = session.get(User.class, id);
            transaction.commit();
            session.close();

            if(result == null) {
                System.out.print("111");
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception();
    }

    public void createUser(@NotNull User user) {
        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update(@NotNull User user) {
        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteById(@NotNull Long id) {
        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
