package com.gihub.fil1n.dao;

import com.gihub.fil1n.HibernateInit;
import com.gihub.fil1n.models.City;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CityDao {
    private static Session session;
    private static Transaction transaction;

    public City getByName(@NotNull String name) {
        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<City> query = builder.createQuery(City.class);
            Root<City> root = query.from(City.class);
            query.select(root).where(builder.equal(root.get("name"), name));
            Query q = session.createQuery(query);
            List<City> cityList = q.getResultList();
            return cityList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
