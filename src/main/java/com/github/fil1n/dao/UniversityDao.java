package com.github.fil1n.dao;

import com.github.fil1n.HibernateInit;
import com.github.fil1n.models.City;
import com.github.fil1n.models.Faculty;
import com.github.fil1n.models.University;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UniversityDao {
    private static CityDao cityDao = new CityDao();


    public List<University> getByCity(@NotNull String city) throws Exception {

        try {
            City result = cityDao.getByName(city);
            return result.getUniversities();
        }catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception();
    }

    public List<Faculty> getFacultiesByUniversityId(@NotNull String id) throws Exception {
        Session session;
        Transaction transaction;

        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            University university = session.get(University.class, Long.valueOf(id));
            transaction.commit();
            session.close();
            return university.getFacultiesList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception();
    }

    public Faculty getFacultyByName(@NotNull String name) throws Exception {
        Session session;
        Transaction transaction;

        try {
            session = HibernateInit.getSessionFactory().openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Faculty> query = builder.createQuery(Faculty.class);
            Root<Faculty> root = query.from(Faculty.class);
            query.select(root).where(builder.equal(root.get("name"), name));
            Query q = session.createQuery(query);
            List<Faculty> faculties = q.getResultList();
            session.close();

            return faculties.get(0);
        }catch (Exception e) {
            e.printStackTrace();
        }

        throw new Exception();
    }

}
