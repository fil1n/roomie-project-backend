package com.github.fil1n.dao;

import com.github.fil1n.HibernateInit;
import com.github.fil1n.models.City;
import com.github.fil1n.models.Country;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CityDao {

    public City getByName(@NotNull String name) {
        Session session;
        Transaction transaction;

        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<City> query = builder.createQuery(City.class);
            Root<City> root = query.from(City.class);
            query.select(root).where(builder.equal(root.get("name"), name));
            Query q = session.createQuery(query);
            List<City> cityList = q.getResultList();
            transaction.commit();
            session.close();
            return cityList.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<City> getByCountry (@NotNull String country) throws Exception {
        Session session;
        Transaction transaction;

        try {
            session = HibernateInit.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Country> query = builder.createQuery(Country.class);
            Root<Country> root = query.from(Country.class);
            query.select(root).where(builder.equal(root.get("name"), country));
            Query q = session.createQuery(query);
            List<Country> countries = q.getResultList();
            session.close();
            return  countries.get(0).getCityList();
        }catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception();
    }

    public List<City> getAll() {
        Session session;
        Transaction transaction;


        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<City> query = builder.createQuery(City.class);
            Root<City> root = query.from(City.class);
            query.select(root);
            Query q = session.createQuery(query);
            transaction.commit();
            session.close();
            return q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void add(@NotNull City city) {
        Session session;
        Transaction transaction;

        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public City getById(@NotNull Long id) throws Exception {
        Session session;
        Transaction transaction;

        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            City city = session.get(City.class, id);
            transaction.commit();
            session.close();

            return city;
        }catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception();
    }

}
