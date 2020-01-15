package com.github.fil1n;

import com.github.fil1n.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class HibernateInit {

    private static SessionFactory sessionFactory;

    static {
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure().build();

        try {
            MetadataSources sources = new MetadataSources(registry);

            sources.addPackage("models").addAnnotatedClass(User.class)
                    .addAnnotatedClass(Group.class)
            .addAnnotatedClass(Question.class).addAnnotatedClass(CommunicationType.class)
                    .addAnnotatedClass(Habbit.class).addAnnotatedClass(UserPreferences.class).
                    addAnnotatedClass(LangPreferences.class).addAnnotatedClass(Country.class).
                    addAnnotatedClass(City.class).addAnnotatedClass(Faculty.class).
                    addAnnotatedClass(University.class).addAnnotatedClass(Language.class);

            sessionFactory = sources.buildMetadata().buildSessionFactory();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SessionFactory getSessionFactory() throws Exception {
        if (sessionFactory == null) throw new Exception();

        return sessionFactory;
    }

    private HibernateInit() {}
}
