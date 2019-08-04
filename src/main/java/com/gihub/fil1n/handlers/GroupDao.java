package com.gihub.fil1n.handlers;

import com.gihub.fil1n.HibernateInit;
import com.gihub.fil1n.models.Group;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;

public class GroupDao {
    private Session session;
    private Transaction transaction;

    public void addGroup(@NotNull Group group) {
        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(group);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Group getGroup(@NotNull String id) {
        try {
            Long groupId = Long.valueOf(id);
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Group group = session.get(Group.class, groupId);
            transaction.commit();
            session.close();

            return group;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void deleteGroup(@NotNull String id) {
        try {
            Long groupId = Long.valueOf(id);
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(getGroup(id));
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateGroup(@NotNull Group group) {
        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(group);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
