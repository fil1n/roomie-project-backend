package com.gihub.fil1n;

import com.gihub.fil1n.handlers.UserHandlers;
import com.gihub.fil1n.models.User;
import io.javalin.Javalin;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        User user = new User();

        user.setFirstName("123");
        user.setLastName("312");

        Session session;
        Transaction transaction;

        try {

            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(user);

            transaction.commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Javalin app = Javalin.create().port(9200).enableDebugLogging();

        app.routes(() ->
                {
                    app.get("/user/:id", ctx -> UserHandlers.getUserById(ctx.pathParam("id") , ctx));
                }
        );

        app.start();
    }
}
