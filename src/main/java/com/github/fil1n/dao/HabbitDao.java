package com.github.fil1n.dao;

import com.github.fil1n.HibernateInit;
import com.github.fil1n.models.Habbit;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HabbitDao {

    public Habbit getByName(String name) throws Exception {
        Session session;
        Transaction transaction;

        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Habbit> query = builder.createQuery(Habbit.class);
            Root<Habbit> root = query.from(Habbit.class);
            query.select(root).where(builder.equal(root.get("name"), name));
            Query q = session.createQuery(query);
            List<Habbit> cityList = q.getResultList();
            transaction.commit();
            session.close();
            return cityList.get(0);
        }catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception();
    }

    public HabbitDao() {}
}
