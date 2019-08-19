package com.gihub.fil1n.handlers;

import com.gihub.fil1n.Validator;
import com.gihub.fil1n.dao.UserDao;
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

            if(!Validator.isEmailValid(user.getEmail())) {
                ctx.status(422);
                return;
            }

            dao.createUser(user);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteUserById(@NotNull String id) {
        try {
            Long longId = Long.valueOf(id);
            dao.deleteById(longId);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
