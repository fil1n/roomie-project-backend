package com.gihub.fil1n.handlers;

import com.gihub.fil1n.models.User;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;

public class UserCRUD {

    private static UserDao dao = new UserDao();
    
    public static void getUserById(@NotNull String id, @NotNull Context ctx) throws Exception {
        User user = null;

        try {
            user = dao.getById(Long.valueOf(id));
            ctx.json(user);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addUser(@NotNull Context ctx) {
        try {
            User user = ctx.bodyAsClass(User.class);
            dao.createUser(user);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
