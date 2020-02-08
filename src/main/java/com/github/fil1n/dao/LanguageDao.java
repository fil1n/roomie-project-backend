package com.github.fil1n.dao;

import com.github.fil1n.HibernateInit;
import com.github.fil1n.models.Language;
import io.javalin.http.Context;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.jetbrains.annotations.NotNull;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class LanguageDao {

    public List<Language> getAll() throws Exception {
        Session session;

        try {
            session = HibernateInit.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Language> query = builder.createQuery(Language.class);
            Root<Language> root = query.from(Language.class);
            query.select(root);
            Query<Language> q = session.createQuery(query);
            List<Language> languages = q.getResultList();
            return languages;
        }catch (Exception e) {}

        throw new Exception();
    }

    public Language getByName(String name) throws Exception {
        Session session;
        Transaction transaction;

        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Language> query = builder.createQuery(Language.class);
            Root<Language> root = query.from(Language.class);
            query.select(root).where(builder.equal(root.get("name"), name));
            javax.persistence.Query q = session.createQuery(query);
            List<Language> cityList = q.getResultList();
            transaction.commit();
            session.close();
            return cityList.get(0);
        }catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception();
    }

    public Language getById(Long id) throws Exception {
        Session session;
        Transaction transaction;

        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Language result = session.get(Language.class, id);
            transaction.commit();
            session.close();
            return result;
        }catch (Exception e) {
            e.printStackTrace();
        }
        throw new Exception();
    }

}
