package com.gihub.fil1n;

import com.gihub.fil1n.models.Group;
import com.gihub.fil1n.models.Question;
import com.gihub.fil1n.models.User;
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

            sources.addPackage("models").addAnnotatedClass(User.class).
                    addAnnotatedClass(Group.class).addAnnotatedClass(Question.class);

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
