package com.github.fil1n.dao;

import com.github.fil1n.HibernateInit;
import com.github.fil1n.models.Group;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jetbrains.annotations.NotNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GroupDao {
    public void addGroup(@NotNull Group group) {
        try {
            Session session = HibernateInit.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(group);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Group getGroupById(@NotNull String id) throws Exception {
        try {
            Long groupId = Long.valueOf(id);
            Session session = HibernateInit.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Group group = (Group) session.get(Group.class, groupId);
            transaction.commit();
            session.close();

            return group;
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception();
    }

    public void deleteGroup(@NotNull String id) {
        try {
            Session session = HibernateInit.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(getGroupById(id));
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateGroup(@NotNull Group group) {
        try {
            Session session = HibernateInit.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(group);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Group> getAllGroups() throws Exception {

        try {
            Session session = HibernateInit.getSessionFactory().openSession();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Group> query = builder.createQuery(Group.class);
            Root<Group> root = query.from(Group.class);
            query.select(root);
            Query<Group> q = session.createQuery(query);
            List<Group> groups = q.getResultList();
            session.close();
            return groups;
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception();
    }

}
