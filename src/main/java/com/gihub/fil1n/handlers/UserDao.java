package com.gihub.fil1n.handlers;

import com.gihub.fil1n.HibernateInit;
import com.gihub.fil1n.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class UserDao {

    public  void create(@NotNull User user) throws Exception {
        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();
            session.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public  User queryForId(@NotNull Long id) throws Exception {
        Transaction transaction;
        Session session = null;

        User user = null;

        try {
            session = HibernateInit.getSessionFactory().openSession();

            transaction = session.beginTransaction();

             user = session.get(User.class, id);

            transaction.commit();

            return user;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
        }

        if(user == null) throw new Exception();

        return null;
    }


    public  void deleteById(@NotNull final Long id) {

        Transaction transaction = null;
        Session session = null;


        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            User user = session.get(User.class, id);

            session.delete(user);

            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void update(@NotNull User user) {
        Transaction transaction;
        Session session = null;

        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.update(user);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

//    public static ArrayList<User> getAll() {
//
//    }

}
