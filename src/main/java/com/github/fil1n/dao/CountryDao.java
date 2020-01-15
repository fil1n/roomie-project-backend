package com.github.fil1n.dao;

import com.github.fil1n.HibernateInit;
import com.github.fil1n.models.Country;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CountryDao {

    public List<Country> getAllCountries() throws Exception {
        try {
            Session session = HibernateInit.getSessionFactory().openSession();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Country> query = builder.createQuery(Country.class);
            Root<Country> root = query.from(Country.class);
            query.select(root);
            Query<Country> q = session.createQuery(query);
            List<Country> groups = q.getResultList();
            session.close();
            return groups;
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception();
    }

}
