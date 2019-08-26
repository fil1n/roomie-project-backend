package com.gihub.fil1n.dao;

import com.gihub.fil1n.HibernateInit;
import com.gihub.fil1n.models.Image;
import com.sun.istack.NotNull;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ImageDao {

    public void saveImage(@NotNull Image image) {
        Transaction transaction;
        Session session;

        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(image);
            transaction.commit();
            session.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteImage(@NotNull Image image) {
        Transaction transaction;
        Session session;

        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(image);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
