package com.gihub.fil1n.handlers;

import com.gihub.fil1n.HibernateInit;
import com.gihub.fil1n.models.User;
import io.javalin.Context;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jetbrains.annotations.NotNull;

public class UserHandlers {

    public static void getUserById(@NotNull String id, @NotNull Context ctx) {
        Session session;
        Transaction transaction;

        try {
            session = HibernateInit.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User user =  session.get(User.class, Long.valueOf(id));
            transaction.commit();
            session.close();

            ctx.json(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
